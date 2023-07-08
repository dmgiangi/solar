package dev.dmgiangi.solar.probe.exception;

import dev.dmgiangi.solar.probe.Probe;

public class ProbeChecksumException extends OneWireProbeException {
    private static final long serialVersionUID = 2723115283949391349L;

    public ProbeChecksumException(Probe probe) {
        super(probe);
    }

    @Override
    public String getClientMessage() {
        return getStandardMessage() + " - Checksum error.";
    }
}
