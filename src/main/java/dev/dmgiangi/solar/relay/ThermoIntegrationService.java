package dev.dmgiangi.solar.relay;

import com.pi4j.io.gpio.digital.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ThermoIntegrationService {
    private final DigitalOutput thermoIntegration;

    public ThermoIntegrationService(@Qualifier("thermoIntegration") DigitalOutput thermoIntegration) {
        this.thermoIntegration = thermoIntegration;
    }
}
