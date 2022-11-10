package com.dashboard.api.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dashboard.api.Service.YoutubeService;

@Controller
@RequestMapping("widget/youtube/video")
public class YoutubeVideoController extends WidgetContoller {

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public Object createVideo(@RequestBody String body, YoutubeService youtubeService) {
        return super.createWidget(body, youtubeService);
    }

    @RequestMapping(path = "update/{id}", method = RequestMethod.POST)
    public Object updateVideo(@PathVariable(value = "id") String id, @RequestBody String body,
            YoutubeService youtubeService) {
        return super.updateWidget(Integer.parseInt(id), body, youtubeService);
    }

    @RequestMapping(path = "/updateData/{id}")
    public Object UpdateDataVideo(@PathVariable(value = "id") String id, YoutubeService youtubeService) {
        return super.updateData(Integer.parseInt(id), youtubeService);
    }
}
