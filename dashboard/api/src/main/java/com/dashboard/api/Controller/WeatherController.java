package com.dashboard.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Service.WeatherService;

@RestController
@RequestMapping("widget/weather")
public class WeatherController extends WidgetContoller {

    @Autowired
    WeatherService weatherService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Object createWidget(@RequestBody String body) {
        return super.createWidget(body, this.weatherService);
    }

    @RequestMapping(path = "/updateCity/{id}", method = RequestMethod.POST)
    public Object UpdateCity(@PathVariable(value = "id") String id, @RequestBody String body) {
        return super.updateWidget(Integer.parseInt(id), body, this.weatherService);
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id) {
        return super.updateData(Integer.parseInt(id), this.weatherService);
    }
}
