package com.dashboard.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Youtube_channel")
public class YoutubeChannel extends Youtube {

    @Getter
    private final static String API_SEARCH = "search?type=channel";
    @Getter
    private final static String API_CHANNEL = "channels?part=statistics,snippet";

    private String channel;
}
