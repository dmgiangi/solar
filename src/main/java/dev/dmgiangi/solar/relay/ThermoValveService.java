package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.input.one_wire.ProbesService;
import dev.dmgiangi.solar.output.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ThermoValveService implements RelayService {
    private final DigitalOutput thermoValve;
    private final ProbesService probesService;
    private String status;

    public ThermoValveService(
            @Qualifier("thermoValve") DigitalOutput thermoValve,
            ProbesService probesService) {
        this.thermoValve = thermoValve;
        this.probesService = probesService;
    }

    @Override
    public void compute() {
        Double thermoFirePlace = probesService.getThermoFirePlace();
        Double boilerMiddle = probesService.getBoilerMiddle();

        if (boilerMiddle < thermoFirePlace - 7) {
            thermoValve.on();
            status = "On";
        } else if (boilerMiddle > thermoFirePlace - 4) {
            thermoValve.off();
            status = "Off";
        }
    }

    @Override
    public RelayStatus getStatus() {
        return new RelayStatus("Thermo Valve", status, true);
    }
}
