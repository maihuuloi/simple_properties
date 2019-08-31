package com.lhm.properties.exception;

public class SetValueToFieldFailedException extends RuntimeException {
    public SetValueToFieldFailedException() {
    }

    public SetValueToFieldFailedException(String message) {
        super(message);
    }

    public SetValueToFieldFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SetValueToFieldFailedException(Throwable cause) {
        super(cause);
    }

}
