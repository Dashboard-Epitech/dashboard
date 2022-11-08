package com.dashboard.api.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Youtube;
import com.dashboard.api.Repository.WidgetRepository;

@Service
public class YoutubeService extends WidgetService {

    public Object createWidget(String body, WidgetRepository widgetRepository) {
        Youtube youtube = new Youtube();
        JSONObject input = new JSONObject(body);

        String channel = input.getString("channel");
        if (!channel.isBlank())
            youtube.setChannel(channel);

        widgetRepository.save(youtube);

        return youtube;
    }

    public Object updateChannel(int id, String body, WidgetRepository widgetRepository) throws Exception {
        Youtube youtube = super.getInstanceOf(Youtube.class, id, widgetRepository);

        JSONObject input = new JSONObject(body);

        String channel = input.getString("channel");
        if (!channel.isBlank())
            youtube.setChannel(channel);
        else
            youtube.setChannel(null);

        widgetRepository.save(youtube);

        return youtube;
    }

    public String updateData(int id, WidgetRepository widgetRepository) throws Exception {
        Youtube youtube = super.getInstanceOf(Youtube.class, id, widgetRepository);

        if (youtube.getChannel() == null)
            throw new Exception("Not channel");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(
                        Youtube.getAPI_URL() + Youtube.getAPI_SEARCH() + "&key=" + Youtube.getAPI_KEY()
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
                        Youtube.getAPI_URL() + Youtube.getAPI_CHANNEL() + "&key=" + Youtube.getAPI_KEY() + "&id="
                                + idChannel))
                .build();

        response = httpClient.send(request, BodyHandlers.ofString());

        System.out.println(response.body());

        return response.body();
    }
}
