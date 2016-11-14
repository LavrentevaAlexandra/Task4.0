package com.lavr.fourth.action;

/**
 * Created by 123 on 13.11.2016.
 */
public class PolskaFormException extends Exception {
    public PolskaFormException() {
    }

    public PolskaFormException(String message) {
        super(message);
    }

    public PolskaFormException(String message, Throwable cause) {
        super(message, cause);
    }

    public PolskaFormException(Throwable cause) {
        super(cause);
    }
}
