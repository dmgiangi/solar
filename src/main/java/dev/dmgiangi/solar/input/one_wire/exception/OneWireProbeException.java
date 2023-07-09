package dev.dmgiangi.solar.input.one_wire.exception;

import dev.dmgiangi.solar.error.SolarException;
import dev.dmgiangi.solar.input.one_wire.Probe;
import lombok.Getter;

@Getter
public abstract class OneWireProbeException extends SolarException {
    private static final long serialVersionUID = -1204087294021987740L;

    private final Probe probe;

    public OneWireProbeException(Probe probe) {
        this.probe = probe;
    }

    public OneWireProbeException(String message, Probe probe) {
        super(message);
        this.probe = probe;
    }

    public OneWireProbeException(String message, Throwable cause, Probe probe) {
        super(message, cause);
        this.probe = probe;
    }

    public String getStandardMessage() {
        return String.format("Error reading probe [%s]", probe.getName());
    }
}
