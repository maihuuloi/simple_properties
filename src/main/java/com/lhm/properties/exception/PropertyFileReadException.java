package com.lhm.properties.exception;

public class PropertyFileReadException extends RuntimeException {
    public PropertyFileReadException() {
    }

    public PropertyFileReadException(String message) {
        super(message);
    }

    public PropertyFileReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public PropertyFileReadException(Throwable cause) {
        super(cause);
    }

    public PropertyFileReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
