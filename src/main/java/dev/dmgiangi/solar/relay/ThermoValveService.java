package dev.dmgiangi.solar.relay;

import com.pi4j.io.gpio.digital.DigitalOutput;
import dev.dmgiangi.solar.probe.ProbesService;
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
