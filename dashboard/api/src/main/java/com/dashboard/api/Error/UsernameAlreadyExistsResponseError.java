package com.dashboard.api.Error;


public class UsernameAlreadyExistsResponseError extends GenericResponseError {
    public UsernameAlreadyExistsResponseError() {
        super();
        this.errorTypeValue = "uniqueUsernameError";
        this.errorContentsValue = "uniqueUsernameAlreadyInUse";
    }
}