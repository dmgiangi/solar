package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.output.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ThermoIntegrationService implements RelayService {
    @Override
    public void compute() {

    }

    private final DigitalOutput thermoIntegration;

    public ThermoIntegrationService(@Qualifier("thermoIntegration") DigitalOutput thermoIntegration) {
        this.thermoIntegration = thermoIntegration;
    }
}
