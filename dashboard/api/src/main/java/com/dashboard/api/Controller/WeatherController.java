package com.dashboard.api.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Service.WeatherService;

@RestController
@RequestMapping("widget/weather")
public class WeatherController extends WidgetContoller {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Object createWidget(@RequestBody String body, WeatherService weatherService) {
        return super.createWidget(body, weatherService);
    }

    @RequestMapping(path = "/updateCity/{id}", method = RequestMethod.POST)
    public Object UpdateCity(@PathVariable(value = "id") String id, @RequestBody String body,
            WeatherService weatherService) {
        return super.updateWidget(Integer.parseInt(id), body, weatherService);
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id, WeatherService weatherService) {
        return super.updateData(Integer.parseInt(id), weatherService);
    }
}
