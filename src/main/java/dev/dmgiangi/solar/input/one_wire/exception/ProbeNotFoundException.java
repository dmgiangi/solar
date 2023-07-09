package dev.dmgiangi.solar.input.one_wire.exception;

import dev.dmgiangi.solar.input.one_wire.Probe;

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
