package dev.dmgiangi.solar.probe.exception;

import dev.dmgiangi.solar.probe.Probe;

public class ProbeValueException extends OneWireProbeException {
    private static final long serialVersionUID = 6032250775484383365L;

    public ProbeValueException(Probe probe) {
        super(probe);
    }

    @Override
    public String getClientMessage() {
        return getStandardMessage() + " - Malformed value error.";
    }
}
