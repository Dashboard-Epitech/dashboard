package com.dashboard.api.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WeatherApiService {
    @Value("${WEATHER_API_KEY}")
    private String api_key;

    private final static String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public String getWeather(String city, String unit) throws Exception {
        try {
            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(API_URL + "?q=" + city.replaceAll(" ", "+") + "&appid="
                    + this.api_key + "&units=" + (unit.equals("C") ? "metric" : "imperial")))
            .build();

            HttpClient httpClient = HttpClient.newHttpClient();

            HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

            return response.body();
        } catch (Exception ex) {
            throw ex;
        }
    } 

    // public String weatherCity(String city, boolean isCelsius) throws Exception {
    //     HttpRequest request = HttpRequest.newBuilder()
    //             .uri(new URI(API_URL + "?q=" + city.replaceAll(" ", "+") + "&appid="
    //                     + this.api_key + "&units=" + (isCelsius ? "metric" : "imperial")))
    //             .build();

    //     HttpClient httpClient = HttpClient.newHttpClient();

    //     HttpResponse<String> response = httpClient.send(request, BodyHandlers.ofString());

    //     return response.body();
    // }
}
