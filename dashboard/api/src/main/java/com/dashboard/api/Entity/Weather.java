package com.dashboard.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Weather")
public class Weather extends Widget {

    private final static String API_KEY = "248f71af2576da84a291a06fba0a8fdd";
    private final static String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private String city;

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getApiUrl() {
        return API_URL;
    }
}
