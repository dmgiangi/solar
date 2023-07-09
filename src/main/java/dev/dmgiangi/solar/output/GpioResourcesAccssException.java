package dev.dmgiangi.solar.output;

public class GpioResourcesAccssException extends RuntimeException {
    private static final long serialVersionUID = -3790285163494839374L;

    public GpioResourcesAccssException() {
    }

    public GpioResourcesAccssException(String message) {
        super(message);
    }

    public GpioResourcesAccssException(String message, Throwable cause) {
        super(message, cause);
    }

    public GpioResourcesAccssException(Throwable cause) {
        super(cause);
    }

    public GpioResourcesAccssException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
