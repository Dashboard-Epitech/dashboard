package com.dashboard.api.Exception;

public class EmailAlreadyInUseException extends Exception {
    public EmailAlreadyInUseException() {
        super("Email already in use. Please try another.");
    }
}
