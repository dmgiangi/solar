package dev.dmgiangi.solar.probe.exception;

import dev.dmgiangi.solar.probe.Probe;

public class ProbeNotFoundException extends OneWireProbeException {
    private static final long serialVersionUID = 7733973752438296181L;

    public ProbeNotFoundException(Probe probe) {
        super(probe);
    }

    @Override
    public String getClientMessage() {
        return getStandardMessage() + " - Probe not found.";
    }
}
