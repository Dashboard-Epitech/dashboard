package com.dashboard.api.Error;


public class UsernameAlreadyExistsResponseError extends GenericResponseError<String> {
    public UsernameAlreadyExistsResponseError() {
        super();
        this.errorTypeValue = "uniqueUsernameError";
        this.errorContentsValue = "An user with this email/username is already registered.";
    }
}