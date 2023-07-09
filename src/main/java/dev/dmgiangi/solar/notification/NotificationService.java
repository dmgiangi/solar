package dev.dmgiangi.solar.notification;

import dev.dmgiangi.solar.input.one_wire.Probe;
import dev.dmgiangi.solar.input.one_wire.exception.OneWireProbeException;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NotificationService {
    public void probeReadingErrors(Map<Probe, OneWireProbeException> errors) {
    }
}
