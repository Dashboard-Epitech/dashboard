package com.dashboard.api.Error;

import java.util.HashMap;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class GenericResponseError {
    protected final String errorTypeKey = "errorType";
    protected final String errorContentsKey = "errorContents";
    protected String errorTypeValue = null;
    protected String errorContentsValue = null;

    public Map<String, String> build() {
        Map<String, String> error = new HashMap<>();
        error.put(this.errorTypeKey, this.errorTypeValue);
        error.put(this.errorContentsKey, this.errorContentsValue);

        return error;
    }
}
