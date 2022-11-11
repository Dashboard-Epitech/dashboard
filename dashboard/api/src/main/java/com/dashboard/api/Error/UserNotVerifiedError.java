package com.dashboard.api.Error;

public class UserNotVerifiedError extends GenericResponseError<String> {
    public UserNotVerifiedError() {
        super();
        this.errorTypeValue = "userNotVerifiedError";
        this.errorContentsValue = "Please verify your account before logging in.";
    }
}
