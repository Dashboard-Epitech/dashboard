package com.dashboard.api.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Weather;
import com.dashboard.api.Request.WeatherRequest;
import com.dashboard.api.Request.WidgetRequest;

@Service
public class WeatherService extends WidgetService {

    @Value("${WEATHER_API_KEY}")
    private String api_key;

    private final static String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Override
    public Object createWidget() {
        Weather weather = new Weather();

        widgetRepository.save(weather);

        return weather;
    }

    @Override
    public <W extends WidgetRequest> Object updateWidget(int id, W request) throws Exception {
        Weather weather = super.getInstanceOf(Weather.class, id);
        WeatherRequest weatherRequest = (WeatherRequest) request;
        String city = weatherRequest.getCity();

        if (!this.cityValide(city))
            throw new Exception(city + " not found");

        weather.setCity(city);

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

        return this.weatherCity(weather.getCity());
    }

    public String weatherCity(String city) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "?q=" + city.replaceAll(" ", "+") + "&appid="
                        + this.api_key))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
    }

    private boolean cityValide(String city) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(API_URL + "?q=" + city.replaceAll(" ", "+") + "&appid="
                            + this.api_key))
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

            return response.statusCode() == 200;
        } catch (Exception e) {
            return false;
        }
    }
}
