package com.dashboard.api.Model.Request.widget.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWeatherWidgetRequest {
    private Long dashboardId;

    private String city;

    private String unit;

    private String size;

    private Long refreshRate;
}
