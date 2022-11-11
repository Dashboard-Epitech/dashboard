package com.dashboard.api.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dashboard.api.Service.YoutubeChannelService;

@Controller
@RequestMapping("widget/youtube/channel")
public class YoutubeChannelController extends WidgetContoller {

    @Autowired
    YoutubeChannelService youtubeChannelService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public Object createChannel(@RequestBody String body) {
        return super.createWidget(body, this.youtubeChannelService);
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public Object updateChannel(@PathVariable(value = "id") String id, @RequestBody String body) {
        return super.updateWidget(Integer.parseInt(id), body, this.youtubeChannelService);
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id) {
        return super.updateData(Integer.parseInt(id), this.youtubeChannelService);
    }
}
