package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.output.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SolarPumpService {
    private final DigitalOutput solarPump;

    public SolarPumpService(@Qualifier("solarPump") DigitalOutput solarPump) {
        this.solarPump = solarPump;
    }


}
