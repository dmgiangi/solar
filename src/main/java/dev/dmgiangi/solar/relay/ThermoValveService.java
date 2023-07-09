package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.input.one_wire.ProbesService;
import dev.dmgiangi.solar.output.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ThermoValveService {
    private final DigitalOutput thermoValve;
    private final ProbesService probesService;

    public ThermoValveService(
            @Qualifier("thermoValve") DigitalOutput thermoValve,
            ProbesService probesService) {
        this.thermoValve = thermoValve;
        this.probesService = probesService;
    }
}
