package com.dashboard.api.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Model.Request.api.weather.GetWeatherRequest;
import com.dashboard.api.Request.WeatherRequest;
// import com.dashboard.api.Service.WeatherService;
import com.dashboard.api.Service.WeatherApiService;

@RestController
@RequestMapping("weather_api")
@CrossOrigin(originPatterns = "http://localhost:*")
public class WeatherController {

    @Autowired
    private WeatherApiService weatherApiService;

    @PostMapping(path = "/get")
    public ResponseEntity<?> getWeather(@RequestBody GetWeatherRequest request) {
        try {
            return ResponseEntity.ok().body(weatherApiService.getWeather(request.getCity(), request.getUnit()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    // @RequestMapping(path = "/create", method = RequestMethod.POST)
    // public ResponseEntity<?> createWidget(@RequestBody @Valid WeatherRequest request) {
    //     return super.createWidget(request, this.weatherService);
    // }

    // @RequestMapping(path = "/update/field/{id}", method = RequestMethod.PATCH)
    // public ResponseEntity<?> UpdateCity(@PathVariable(value = "id") String id,
    //         @RequestBody @Valid WeatherRequest request) {
    //     return super.updateWidget(Long.parseLong(id), request, this.weatherService);
    // }

    // @RequestMapping(path = "/update/{id}")
    // public ResponseEntity<?> UpdateDataWidget(@PathVariable(value = "id") String id) {
    //     return super.updateData(Long.parseLong(id), this.weatherService);
    // }

    // @RequestMapping(path = "/search", method = RequestMethod.POST)
    // public ResponseEntity<?> shearchCity(@RequestBody @Valid WeatherRequest body) {
    //     try {
    //         return ResponseEntity.ok().body(this.weatherService.weatherCity(body.getCity(), body.getIsCelsius()));
    //     } catch (Exception e) {
    //         return ResponseEntity.badRequest().body(e.getMessage());
    //     }
    // }

}
