package com.dashboard.api.Entity;

import javax.persistence.Entity;

import lombok.Getter;

@Entity
public abstract class Youtube extends Widget {
    @Getter
    protected final static String API_KEY = "AIzaSyBFlfmNuoXL8YTZirp8feOKqcd1q79iOSU";
    @Getter
    protected final static String API_URL = "https://www.googleapis.com/youtube/v3/";
}
