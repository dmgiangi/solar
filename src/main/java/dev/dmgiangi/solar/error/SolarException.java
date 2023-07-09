package dev.dmgiangi.solar.error;

public abstract class SolarException extends RuntimeException {
    private static final long serialVersionUID = 7760828129287103567L;

    public SolarException() {
    }

    public SolarException(String message) {
        super(message);
    }

    public SolarException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract String getClientMessage();
}
