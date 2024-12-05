package com.agent.provider.exception;

public class UnsupportedEventException extends IllegalArgumentException {

    private static final long serialVersionUID = 8080003451087681360L;

    public UnsupportedEventException(String message) {
        super(message);
    }

}
