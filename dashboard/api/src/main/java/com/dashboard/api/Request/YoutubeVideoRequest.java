package com.dashboard.api.Request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;

@Getter
public class YoutubeVideoRequest extends WidgetRequest {

    @NotBlank(message = "Please enter a video.")
    private String video;
}
