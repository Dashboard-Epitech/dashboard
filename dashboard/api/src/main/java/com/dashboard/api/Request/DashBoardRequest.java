package com.dashboard.api.Request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class DashBoardRequest {
    @NotNull(message = "Please enter a name for your dashboard")
    private String name;
}
