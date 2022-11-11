package com.dashboard.api.Error;

public class MailResponseError extends GenericResponseError<String> {
    public MailResponseError(String errorMessage) {
        super();
        this.errorTypeValue = "mailError";
        this.errorContentsValue = "An error has occurred while sending an email : " + errorMessage;
    }
}
