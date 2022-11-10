package com.dashboard.api.Error;

public class BadCredentialsError extends GenericResponseError {
    public BadCredentialsError(String errorMessage) {
        super();
        this.errorTypeValue = "badCredentialsError";
        this.errorContentsValue = errorMessage;
    }
}
