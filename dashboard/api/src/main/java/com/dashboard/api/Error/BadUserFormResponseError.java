package com.dashboard.api.Error;

import java.util.Map;

public class BadUserFormResponseError extends GenericResponseError<Map> {
    public BadUserFormResponseError(Map errors) {
        super();
        this.errorTypeValue = "badUserFormError";
        this.errorContentsValue = errors;
    }
}
