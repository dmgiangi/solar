package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.input.one_wire.ProbesService;
import dev.dmgiangi.solar.output.DigitalOutput;
import dev.dmgiangi.solar.status.RedisStatusService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FanCoilService implements RelayService {
    private final RedisStatusService redisStatusService;

    private final DigitalOutput fanCoil1;
    private final DigitalOutput fanCoil2;
    private final DigitalOutput fanCoil3;
    private final ProbesService probesService;
    private FanCoilStatus actual;
    private boolean enabled;
    public FanCoilService(
            @Qualifier("fanCoil1") DigitalOutput fanCoil1,
            @Qualifier("fanCoil2") DigitalOutput fanCoil2,
            @Qualifier("fanCoil3") DigitalOutput fanCoil3,
            RedisStatusService redisStatusService,
            ProbesService probesService) {
        this.fanCoil1 = fanCoil1;
        this.fanCoil2 = fanCoil2;
        this.fanCoil3 = fanCoil3;

        this.redisStatusService = redisStatusService;
        this.probesService = probesService;

        this.actual = redisStatusService.getFanCoilStatus();
        this.enabled = redisStatusService.isFanCoilEnabled();
    }

    @Override
    public RelayStatus getStatus() {
        return new RelayStatus("Fan Coil", actual.name(), enabled);
    }

    @Override
    public void compute() {
        boolean previousEnabled = enabled;
        final Double thermoFirePlace = probesService.getThermoFirePlace();

        if (thermoFirePlace > 53) {
            enabled = true;
            setStatus(actual);
        } else if (thermoFirePlace < 51) {
            setStatus(FanCoilStatus.OFF);
            enabled = false;
        }

        if (previousEnabled != enabled)
            redisStatusService.setFanCoilEnabled(enabled);
    }

    private void setStatus(@NonNull FanCoilStatus status) {
        log.trace("SETTING FAN COIL STATUS {}", status);
        switch (status) {
            case ONE:
                fanCoil1.on();
                fanCoil2.off();
                fanCoil3.off();
                break;
            case TWO:
                fanCoil1.off();
                fanCoil2.on();
                fanCoil3.off();
                break;
            case THREE:
                fanCoil1.off();
                fanCoil2.off();
                fanCoil3.on();
                break;
            case OFF:
                fanCoil1.off();
                fanCoil2.off();
                fanCoil3.off();
        }
    }

    public void step() {
        int status = actual.getCode();
        if (status >= 3)
            status = 0;
        else status = status + 1;

        log.trace("FAN COIL STEP CALLED, GOING FROM STATE {} TO {}", actual.getCode(), status);

        switch (status) {
            case 1:
                this.actual = FanCoilStatus.ONE;
                break;
            case 2:
                this.actual = FanCoilStatus.TWO;
                break;
            case 3:
                this.actual = FanCoilStatus.THREE;
                break;
            case 0:
            default:
                this.actual = FanCoilStatus.OFF;
                break;
        }

        log.info("saving new fan coil status {}", actual);
        redisStatusService.setFanCoilStatus(actual);

        if (enabled)
            this.setStatus(actual);
    }
}
