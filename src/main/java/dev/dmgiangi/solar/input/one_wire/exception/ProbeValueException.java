package dev.dmgiangi.solar.input.one_wire.exception;

import dev.dmgiangi.solar.input.one_wire.Probe;

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
