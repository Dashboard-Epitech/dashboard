package com.dashboard.api.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Request.WeatherRequest;
import com.dashboard.api.Service.WeatherService;

@RestController
@RequestMapping("widget/weather")
public class WeatherController extends WidgetContoller {

    @Autowired
    WeatherService weatherService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createWidget(@RequestBody @Valid WeatherRequest request) {
        return super.createWidget(request, this.weatherService);
    }

    @RequestMapping(path = "/update/field/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> UpdateCity(@PathVariable(value = "id") String id,
            @RequestBody @Valid WeatherRequest request) {
        return super.updateWidget(Long.parseLong(id), request, this.weatherService);
    }

    @RequestMapping(path = "/update/{id}")
    public ResponseEntity<?> UpdateDataWidget(@PathVariable(value = "id") String id) {
        return super.updateData(Long.parseLong(id), this.weatherService);
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ResponseEntity<?> shearchCity(@RequestBody @Valid WeatherRequest body) {
        try {
            return ResponseEntity.ok().body(this.weatherService.weatherCity(body.getCity(), body.getIsCelsius()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
