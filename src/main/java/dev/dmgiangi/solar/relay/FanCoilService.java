package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.output.DigitalOutput;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FanCoilService {
    private final DigitalOutput fanCoil1;
    private final DigitalOutput fanCoil2;
    private final DigitalOutput fanCoil3;
    private FanCoilStatus actual = FanCoilStatus.OFF;

    public FanCoilService(
            @Qualifier("fanCoil1") DigitalOutput fanCoil1,
            @Qualifier("fanCoil2") DigitalOutput fanCoil2,
            @Qualifier("fanCoil3") DigitalOutput fanCoil3
    ) {
        this.fanCoil1 = fanCoil1;
        this.fanCoil2 = fanCoil2;
        this.fanCoil3 = fanCoil3;
        this.setStatus(FanCoilStatus.OFF);
    }

    public void setStatus(@NonNull FanCoilStatus status){
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

        this.setStatus(actual);
    }
}
