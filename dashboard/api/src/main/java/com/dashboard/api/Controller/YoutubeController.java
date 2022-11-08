package com.dashboard.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dashboard.api.Service.YoutubeService;

@Controller
@RequestMapping("widget/youtube")
public class YoutubeController extends WidgetContoller {

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Object createWidget(@RequestBody String body, YoutubeService youtubeService) {
        return youtubeService.createWidget(body, this.widgetRepository);
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public Object updateCurrencies(@PathVariable(value = "id") String id, @RequestBody String body,
            YoutubeService youtubeService) {
        try {
            return youtubeService.updateChannel(Integer.parseInt(id), body, this.widgetRepository);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id, YoutubeService youtubeService) {
        try {
            return youtubeService.updateData(Integer.parseInt(id), this.widgetRepository);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
