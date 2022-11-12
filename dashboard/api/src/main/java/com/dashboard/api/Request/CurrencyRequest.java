package com.dashboard.api.Request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class CurrencyRequest extends WidgetRequest {

    @NotBlank(message = "Please enter a from currency.")
    private String from;

    private String to;
}
