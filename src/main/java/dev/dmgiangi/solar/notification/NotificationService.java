package dev.dmgiangi.solar.notification;

import dev.dmgiangi.solar.probe.Probe;
import dev.dmgiangi.solar.probe.exception.OneWireProbeException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationService {
    public void probeReadingErrors(Map<Probe, OneWireProbeException> errors) {
    }
}
