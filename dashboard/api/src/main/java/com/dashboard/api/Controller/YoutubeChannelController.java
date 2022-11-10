package com.dashboard.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dashboard.api.Service.YoutubeChannelService;

@Controller
@RequestMapping("widget/youtube/channel")
public class YoutubeChannelController extends WidgetContoller {

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public Object createChannel(@RequestBody String body, YoutubeChannelService youtubeChannelService) {
        return super.createWidget(body, youtubeChannelService);
    }

    @RequestMapping(path = "update/{id}", method = RequestMethod.POST)
    public Object updateChannel(@PathVariable(value = "id") String id, @RequestBody String body,
            YoutubeChannelService youtubeChannelService) {
        return super.updateWidget(Integer.parseInt(id), body, youtubeChannelService);
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataWidget(@PathVariable(value = "id") String id, YoutubeChannelService youtubeChannelService) {
        return super.updateData(Integer.parseInt(id), youtubeChannelService);
    }
}
