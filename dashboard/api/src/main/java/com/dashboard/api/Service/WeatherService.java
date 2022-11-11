package com.dashboard.api.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Weather;

@Service
public class WeatherService extends WidgetService {

    @Value("${WEATHER_API_KEY}")
    private String api_key;

    private final static String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Override
    public Object createWidget(String body) {
        Weather weather = new Weather();
        JSONObject input = new JSONObject(body);

        String city = input.getString("city");
        if (!city.isBlank())
            weather.setCity(city);

        widgetRepository.save(weather);

        return weather;
    }

    @Override
    public Object updateWidget(int id, String body) throws Exception {
        Weather weather = super.getInstanceOf(Weather.class, id);

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
    public String updateData(int id) throws Exception {
        System.out.println("API KEY : ");
        System.out.println(this.api_key);
        System.out.println("END");
        Weather weather = super.getInstanceOf(Weather.class, id);

        if (weather.getCity() == null)
            throw new Exception(id + " have not city");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "?q=" + weather.getCity().replaceAll(" ", "+") + "&appid="
                        + this.api_key))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
    }
}
