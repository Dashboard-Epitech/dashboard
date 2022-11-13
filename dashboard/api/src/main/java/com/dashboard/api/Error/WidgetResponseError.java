package com.dashboard.api.Error;

import java.util.Map;

public class WidgetResponseError extends GenericResponseError<Map<String, String>> {
    public WidgetResponseError(Map<String, String> errors) {
        super();
        this.errorTypeValue = "WidgetError";
        this.errorContentsValue = errors;
    }
}
