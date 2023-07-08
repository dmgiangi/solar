package dev.dmgiangi.solar.probe.exception;

import dev.dmgiangi.solar.probe.Probe;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class OneWireProbeException extends RuntimeException{
    private static final long serialVersionUID = -1204087294021987740L;

    private final Probe probe;
    protected String getStandardMessage(){
        return String.format("Error reading probe [%s]", probe.getName());
    }
    public abstract String getClientMessage();
}
