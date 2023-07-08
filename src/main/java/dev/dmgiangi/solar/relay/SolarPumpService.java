package dev.dmgiangi.solar.relay;

import com.pi4j.io.gpio.digital.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SolarPumpService {
    private final DigitalOutput solarPump;

    public SolarPumpService(@Qualifier("solarPump") DigitalOutput solarPump) {
        this.solarPump = solarPump;
    }


}
