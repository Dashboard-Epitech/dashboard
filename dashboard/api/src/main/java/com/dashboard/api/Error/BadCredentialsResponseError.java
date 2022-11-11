package com.dashboard.api.Error;

public class BadCredentialsResponseError extends GenericResponseError<String> {
    public BadCredentialsResponseError() {
        super();
        this.errorTypeValue = "badCredentialsError";
        this.errorContentsValue = "Invalid username/password combination.";
    }
}
