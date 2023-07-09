package dev.dmgiangi.solar.output;

import dev.dmgiangi.solar.error.SolarException;

public class GpioResourcesAccessException extends SolarException {
    public static final String MESSAGE = "message";
    private static final long serialVersionUID = -3790285163494839374L;

    public GpioResourcesAccessException(String message) {
        super(message);
    }

    public GpioResourcesAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getClientMessage() {
        return MESSAGE;
    }
}
