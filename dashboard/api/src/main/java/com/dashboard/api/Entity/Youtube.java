package com.dashboard.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Youtube")
public class Youtube extends Widget {

    @Getter
    private final static String API_KEY = "AIzaSyBFlfmNuoXL8YTZirp8feOKqcd1q79iOSU";
    @Getter
    private final static String API_URL = "https://www.googleapis.com/youtube/v3/";
    @Getter
    private final static String API_SEARCH = "search?type=channel";
    @Getter
    private final static String API_CHANNEL = "channels?part=statistics,snippet";

    private String channel;
}
