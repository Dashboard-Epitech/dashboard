package com.dashboard.api.Error;

public class MailResponseError extends GenericResponseError {
    public MailResponseError(String errorMessage) {
        super();
        this.errorTypeValue = "mailError";
        this.errorContentsValue = errorMessage;
    }
}
