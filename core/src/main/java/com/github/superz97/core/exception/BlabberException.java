package com.github.superz97.core.exception;

public class BlabberException extends RuntimeException {

    public BlabberException() {
    }

    public BlabberException(String message) {
        super(message);
    }

    public BlabberException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlabberException(Throwable cause) {
        super(cause);
    }

    public BlabberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}

