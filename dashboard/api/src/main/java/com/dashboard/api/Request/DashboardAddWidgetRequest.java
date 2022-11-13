package com.dashboard.api.Request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class DashboardAddWidgetRequest {

    @NotNull(message = "give dashboard_id")
    private Long dashboard_id;

    @NotNull(message = "give widget_id")
    private Long widget_id;
}
