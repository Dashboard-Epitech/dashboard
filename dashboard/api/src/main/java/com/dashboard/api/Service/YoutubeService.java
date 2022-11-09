package com.dashboard.api.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.YoutubeChannel;
import com.dashboard.api.Repository.WidgetRepository;

@Service
public class YoutubeService extends WidgetService {

    public Object createChannel(String body, WidgetRepository widgetRepository) {
        YoutubeChannel youtube = new YoutubeChannel();
        JSONObject input = new JSONObject(body);

        String channel = input.getString("channel");
        if (!channel.isBlank())
            youtube.setChannel(channel);

        widgetRepository.save(youtube);

        return youtube;
    }

    public Object updateChannel(int id, String body, WidgetRepository widgetRepository) throws Exception {
        YoutubeChannel youtube = super.getInstanceOf(YoutubeChannel.class, id, widgetRepository);

        JSONObject input = new JSONObject(body);

        String channel = input.getString("channel");
        if (!channel.isBlank())
            youtube.setChannel(channel);
        else
            youtube.setChannel(null);

        widgetRepository.save(youtube);

        return youtube;
    }

    public String updateDataChannel(int id, WidgetRepository widgetRepository) throws Exception {
        YoutubeChannel youtube = super.getInstanceOf(YoutubeChannel.class, id, widgetRepository);

        if (youtube.getChannel() == null)
            throw new Exception("Not channel");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        YoutubeChannel.getAPI_URL() + YoutubeChannel.getAPI_SEARCH() + "&key="
                                + YoutubeChannel.getAPI_KEY()
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
                        YoutubeChannel.getAPI_URL() + YoutubeChannel.getAPI_CHANNEL() + "&key="
                                + YoutubeChannel.getAPI_KEY() + "&id="
                                + idChannel))
                .build();

        response = httpClient.send(request, BodyHandlers.ofString());

        System.out.println(response.body());

        return response.body();
    }
}
