package dev.dmgiangi.solar.input.one_wire.exception;

import dev.dmgiangi.solar.input.one_wire.Probe;

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
