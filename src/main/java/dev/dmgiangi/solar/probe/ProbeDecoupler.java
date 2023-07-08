package dev.dmgiangi.solar.probe;

import dev.dmgiangi.solar.notification.NotificationService;
import dev.dmgiangi.solar.probe.exception.OneWireProbeException;
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

    public Double getValue(Probe probe) {
        return values.get(probe.getAddress());
    }

    @Scheduled(initialDelay = 0, fixedDelay = 1000)
    public void task() {
        final Map<Probe, OneWireProbeException> errors = new HashMap<>();

        addresses
                .forEach(probe -> {
                    try {
                        values.put(probe.getAddress(), ProbeReader.getValue(probe));
                    } catch (OneWireProbeException e) {
                        errors.put(probe, e);
                    }
                });

        notificationService.probeReadingErrors(errors);
    }
}
