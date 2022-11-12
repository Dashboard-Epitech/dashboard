package com.dashboard.api.Error;

import java.util.Map;

public class DashboardResponseError extends GenericResponseError<Map<String, String>> {
    public DashboardResponseError(Map<String, String> errors) {
        super();
        this.errorTypeValue = "DashboardError";
        this.errorContentsValue = errors;
    }
}
