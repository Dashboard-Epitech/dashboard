package com.dashboard.api.Error;


public class UsernameAlreadyExistsError extends GenericResponseError {
    public UsernameAlreadyExistsError() {
        super();
        this.errorTypeValue = "uniqueUsernameError";
        this.errorContentsValue = "uniqueUsernameAlreadyInUse";
    }
}