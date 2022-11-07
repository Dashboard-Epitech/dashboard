package com.dashboard.api.Entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("Weather")
public class Weather extends Widget {

    @Getter
    private final static String API_KEY = "248f71af2576da84a291a06fba0a8fdd";
    @Getter
    private final static String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private String city;
}
