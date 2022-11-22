package com.onepo.server.exception;

public class NewPasswordWrong extends RuntimeException{
    public NewPasswordWrong() {
        super();
    }

    public NewPasswordWrong(String message) {
        super(message);
    }

    public NewPasswordWrong(String message, Throwable cause) {
        super(message, cause);
    }

    public NewPasswordWrong(Throwable cause) {
        super(cause);
    }

    protected NewPasswordWrong(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
