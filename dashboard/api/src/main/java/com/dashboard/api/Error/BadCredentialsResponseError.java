package com.dashboard.api.Error;

public class BadCredentialsResponseError extends GenericResponseError {
    public BadCredentialsResponseError(String errorMessage) {
        super();
        this.errorTypeValue = "badCredentialsError";
        this.errorContentsValue = errorMessage;
    }
}
