package com.zhitar.datevalidation.exception;

public class DateValidationException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Error localDate validation";

    public DateValidationException() {
        super(DEFAULT_MESSAGE);
    }

    public DateValidationException(String message) {
        super(message);
    }

    public DateValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateValidationException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    public DateValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
