package com.dashboard.api.Exception;

public class NoDataFoundException extends Exception {

    public NoDataFoundException() {
        super("No Items Found.");
    }

    public NoDataFoundException(String message) {
        super(message);
    }
}
