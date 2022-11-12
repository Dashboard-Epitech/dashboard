package com.dashboard.api.Error;

import java.util.Map;

public class WidgetResponseError extends GenericResponseError<Map> {
    public WidgetResponseError(Map errors) {
        super();
        this.errorTypeValue = "WidgetError";
        this.errorContentsValue = errors;
    }
}
