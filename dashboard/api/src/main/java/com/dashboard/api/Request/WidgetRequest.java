package com.dashboard.api.Request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class WidgetRequest {

    @NotNull(message = "give dashboard_id")
    private Long dashboard_id;
}
