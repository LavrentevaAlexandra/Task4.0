package com.lavr.fourth.entity;

/**
 * Created by 123 on 02.11.2016.
 */
public class LeafOperationException extends Exception {
    public LeafOperationException() {
    }

    public LeafOperationException(String message) {
        super(message);
    }

    public LeafOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeafOperationException(Throwable cause) {
        super(cause);
    }
}
