package dev.dmgiangi.solar.input.one_wire.exception;

import dev.dmgiangi.solar.input.one_wire.Probe;

public class ProbeFileMalformedException extends OneWireProbeException {
    private static final long serialVersionUID = -6109873934729765599L;

    public ProbeFileMalformedException(Probe probe) {
        super(probe);
    }

    @Override
    public String getClientMessage() {
        return getStandardMessage() + " - Malformed File.";
    }
}
