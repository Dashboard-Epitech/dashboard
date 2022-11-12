package com.dashboard.api.Request;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class DashBoardRequest {
    @NotNull(message = "give name")
    private String name;

    @NotNull(message = "give user_id")
    private Long user_id;
}
