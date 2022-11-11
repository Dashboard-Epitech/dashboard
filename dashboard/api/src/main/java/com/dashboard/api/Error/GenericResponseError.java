package com.dashboard.api.Error;

import org.json.JSONObject;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
abstract class GenericResponseError<T> {
    protected final String errorTypeKey = "errorType";
    protected final String errorContentsKey = "errorContents";
    protected String errorTypeValue = null;
    protected T errorContentsValue = null;

    public String build() {
        JSONObject jsonWrapper = new JSONObject();
        jsonWrapper.put(errorTypeKey, errorTypeValue);
        jsonWrapper.put(errorContentsKey, errorContentsValue);

        return jsonWrapper.toString();
    }
}
