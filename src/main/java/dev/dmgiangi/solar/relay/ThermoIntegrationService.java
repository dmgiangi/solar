package dev.dmgiangi.solar.relay;

import dev.dmgiangi.solar.input.one_wire.ProbesService;
import dev.dmgiangi.solar.output.DigitalOutput;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class ThermoIntegrationService implements RelayService {
    private static final LocalTime START_TIME = LocalTime.of(6, 0, 0);
    private static final LocalTime END_TIME = LocalTime.of(20, 0, 0);
    private final ProbesService probesService;
    private final DigitalOutput thermoIntegration;
    private String status = "Off";
    private boolean enabled = false;

    public ThermoIntegrationService(
            @Qualifier("thermoIntegration") DigitalOutput thermoIntegration,
            ProbesService probesService) {
        this.probesService = probesService;
        this.thermoIntegration = thermoIntegration;
    }

    @Override
    public void compute() {
        final LocalTime now = LocalTime.now();
        final Double boilerMiddle = probesService.getBoilerMiddle();

        enabled = now.isAfter(START_TIME) && now.isBefore(END_TIME);

        if (enabled && boilerMiddle < 40) {
            status = "On";
            thermoIntegration.on();
        } else if (boilerMiddle > 43) {
            status = "Off";
            thermoIntegration.off();
        }
    }

    @Override
    public RelayStatus getStatus() {
        return new RelayStatus("Thermo Integration", status, enabled);
    }
}
