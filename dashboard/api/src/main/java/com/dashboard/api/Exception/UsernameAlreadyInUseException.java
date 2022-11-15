package com.dashboard.api.Exception;

public class UsernameAlreadyInUseException extends Exception {
    public UsernameAlreadyInUseException() {
        super("Username already in use. Please try another");
    }
}
