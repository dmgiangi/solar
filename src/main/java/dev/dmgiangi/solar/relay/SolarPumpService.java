package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.input.one_wire.ProbesService;
import dev.dmgiangi.solar.output.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SolarPumpService implements RelayService {
    private final ProbesService probesService;

    private final DigitalOutput solarPump;
    private String status;

    public SolarPumpService(
            @Qualifier("solarPump") DigitalOutput solarPump,
            ProbesService probesService) {
        this.solarPump = solarPump;
        this.probesService = probesService;
    }

    @Override
    public void compute() {
        double min = Double.min(probesService.getSolarIn(), probesService.getSolarOut());
        final Double boilerMiddle = probesService.getBoilerMiddle();
        if (boilerMiddle + 5.0 < min) {
            status = "On";
            solarPump.on();
        } else if (boilerMiddle > min) {
            status = "Off";
            solarPump.off();
        }
    }

    @Override
    public RelayStatus getStatus() {
        return new RelayStatus("Solar Pump", status, true);
    }
}
