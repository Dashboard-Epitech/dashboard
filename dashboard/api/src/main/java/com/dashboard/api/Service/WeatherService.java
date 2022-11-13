package com.dashboard.api.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Weather;
import com.dashboard.api.Entity.Widget;
import com.dashboard.api.Request.WeatherRequest;
import com.dashboard.api.Request.WidgetRequest;

@Service
public class WeatherService extends WidgetService {

    @Value("${WEATHER_API_KEY}")
    private String api_key;

    private final static String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    @Override
    public <W extends WidgetRequest> Object createWidget(W request) throws Exception {
        Weather weather = new Weather();

        return this.save(weather, (WeatherRequest) request);
    }

    @Override
    public <W extends WidgetRequest> Object updateWidget(long id, W request) throws Exception {
        Weather weather = super.getInstanceOf(Weather.class, id);

        return this.save(weather, (WeatherRequest) request);
    }

    @Override
    public String updateData(long id) throws Exception {
        Weather weather = super.getInstanceOf(Weather.class, id);

        if (weather.getCity() == null)
            throw new Exception(id + " have not city");

        return this.weatherCity(weather.getCity(), weather.getIsCelsius());
    }

    public String weatherCity(String city, boolean isCelsius) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "?q=" + city.replaceAll(" ", "+") + "&appid="
                        + this.api_key + "&units=" + (isCelsius ? "metric" : "imperial")))
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

        return response.body();
    }

    @Override
    protected <W extends Widget, R extends WidgetRequest> Object save(W weatherT, R requestT) throws Exception {
        Weather weather = (Weather) weatherT;
        WeatherRequest request = (WeatherRequest) requestT;

        String city = request.getCity();
        boolean isCelsius = request.getIsCelsius();

        if (!this.cityValide(city))
            throw new Exception(city + " not found");

        weather.setCity(city);
        weather.setIsCelsius(isCelsius);

        return super.save(weather, request);
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
