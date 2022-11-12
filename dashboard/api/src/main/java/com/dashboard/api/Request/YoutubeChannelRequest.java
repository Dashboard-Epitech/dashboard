package com.dashboard.api.Request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class YoutubeChannelRequest extends WidgetRequest {

    @NotBlank(message = "Please enter a channel.")
    private String channel;
}
