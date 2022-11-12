package com.dashboard.api.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YoutubeService extends WidgetService {

    @Value("${YOUTUBE_API_KEY}")
    protected String API_KEY;
    protected final static String API_URL = "https://www.googleapis.com/youtube/v3/";
}
