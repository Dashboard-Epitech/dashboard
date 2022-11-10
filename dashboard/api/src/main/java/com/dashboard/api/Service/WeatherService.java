package com.dashboard.api.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Weather;
import com.dashboard.api.Repository.WidgetRepository;

@Service
public class WeatherService extends WidgetService {

    @Override
    public Object createWidget(String body, WidgetRepository widgetRepository) {
        Weather weather = new Weather();
        JSONObject input = new JSONObject(body);

        String city = input.getString("city");
        if (!city.isBlank())
            weather.setCity(city);

        widgetRepository.save(weather);

        return weather;
    }

    @Override
    public Object updateWidget(int id, String body, WidgetRepository widgetRepository) throws Exception {
        Weather weather = super.getInstanceOf(Weather.class, id, widgetRepository);

        JSONObject input = new JSONObject(body);

        String city = input.getString("city");
        if (!city.isBlank())
            weather.setCity(city);
        else
            weather.setCity(null);

        widgetRepository.save(weather);

        return weather;
    }

    @Override
    public String updateData(int id, WidgetRepository widgetRepository) throws Exception {
        Weather weather = super.getInstanceOf(Weather.class, id, widgetRepository);

        if (weather.getCity() == null)
            throw new Exception(id + " have not city");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Weather.getAPI_URL() + "?q=" + weather.getCity().replaceAll(" ", "+") + "&appid="
                        + Weather.getAPI_KEY()))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
    }
}
