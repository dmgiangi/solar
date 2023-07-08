package dev.dmgiangi.solar.probe.exception;

import dev.dmgiangi.solar.probe.Probe;
import dev.dmgiangi.solar.probe.exception.OneWireProbeException;

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
