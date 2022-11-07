package com.dashboard.api.Exception;


public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User Not Found.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
