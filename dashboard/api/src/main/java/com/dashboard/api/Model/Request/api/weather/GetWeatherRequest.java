package com.dashboard.api.Model.Request.api.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetWeatherRequest {
    private String city;
    private String unit;
}
