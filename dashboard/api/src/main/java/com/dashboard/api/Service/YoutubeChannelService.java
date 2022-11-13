package com.dashboard.api.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Widget;
import com.dashboard.api.Entity.YoutubeChannel;
import com.dashboard.api.Request.WidgetRequest;
import com.dashboard.api.Request.YoutubeChannelRequest;

@Service
public class YoutubeChannelService extends YoutubeService {

    private final static String API_SEARCH = "search?type=channel";
    private final static String API_CHANNEL = "channels?part=statistics,snippet";

    @Override
    public <W extends WidgetRequest> Object createWidget(W request) throws Exception {
        YoutubeChannel youtube = new YoutubeChannel();

        return this.save(youtube, (YoutubeChannelRequest) request);
    }

    @Override
    public <W extends WidgetRequest> Object updateWidget(long id, W request) throws Exception {
        YoutubeChannel youtube = super.getInstanceOf(YoutubeChannel.class, id);

        return this.save(youtube, (YoutubeChannelRequest) request);
    }

    @Override
    public String updateData(long id) throws Exception {
        System.out.println(API_KEY);
        YoutubeChannel youtube = super.getInstanceOf(YoutubeChannel.class, id);

        if (youtube.getChannel() == null)
            throw new Exception("Not channel");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        API_URL + API_SEARCH + "&key="
                                + API_KEY
                                + "&q=" + youtube.getChannel()))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());
        System.out.println(response.body());

        JSONArray item = new JSONObject(response.body()).getJSONArray("items");
        String idChannel = item.getJSONObject(0).getJSONObject("id").getString("channelId");
        System.out.println(idChannel);

        request = HttpRequest.newBuilder()
                .uri(new URI(
                        API_URL + API_CHANNEL + "&key="
                                + API_KEY + "&id="
                                + idChannel))
                .build();

        response = httpClient.send(request, BodyHandlers.ofString());

        System.out.println(response.body());

        return response.body();
    }

    @Override
    protected <W extends Widget, R extends WidgetRequest> Object save(W youtube, R request) throws Exception {
        ((YoutubeChannel) youtube).setChannel(((YoutubeChannelRequest) request).getChannel());

        return super.save(youtube, request);
    }
}
