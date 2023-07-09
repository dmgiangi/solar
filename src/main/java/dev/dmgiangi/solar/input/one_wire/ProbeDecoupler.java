package dev.dmgiangi.solar.input.one_wire;

import dev.dmgiangi.solar.input.one_wire.exception.OneWireProbeException;
import dev.dmgiangi.solar.notification.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class ProbeDecoupler {
    private final Map<String, Double> values = new HashMap<>();

    private final List<Probe> addresses;
    private final NotificationService notificationService;
    private final ProbeReader probeReader;
    public Double getValue(Probe probe) {
        return values.get(probe.getAddress());
    }

    @Scheduled(initialDelay = 0, fixedDelay = 1000)
    public void task() {
        final Map<Probe, OneWireProbeException> errors = new HashMap<>();

        addresses
                .forEach(probe -> {
                    try {
                        values.put(probe.getAddress(), probeReader.getValue(probe));
                    } catch (OneWireProbeException e) {
                        errors.put(probe, e);
                    }
                });

        notificationService.probeReadingErrors(errors);
    }
}
