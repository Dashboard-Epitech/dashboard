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
        return weatherService.createWidget(body, this.widgetRepository);
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id, WeatherService weatherService) {
        try {
            return weatherService.updateData(Integer.parseInt(id), this.widgetRepository);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
