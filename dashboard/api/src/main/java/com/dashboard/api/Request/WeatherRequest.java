package com.dashboard.api.Request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class WeatherRequest extends WidgetRequest {

    @NotBlank(message = "Please enter a city.")
    private String city;

    @NotNull(message = "Celsius or Fahrenheit ?")
    private Boolean isCelsius;
}
