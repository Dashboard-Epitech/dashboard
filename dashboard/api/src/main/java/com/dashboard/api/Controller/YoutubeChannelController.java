// package com.dashboard.api.Controller;

// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// import com.dashboard.api.Request.YoutubeChannelRequest;
// import com.dashboard.api.Service.YoutubeChannelService;

// @Controller
// @RequestMapping("widget/youtube/channel")
// public class YoutubeChannelController extends WidgetContoller {

//     @Autowired
//     YoutubeChannelService youtubeChannelService;

//     @RequestMapping(path = "/create", method = RequestMethod.POST)
//     public ResponseEntity<?> createChannel(@RequestBody @Valid YoutubeChannelRequest request) {
//         return super.createWidget(request, this.youtubeChannelService);
//     }

//     @RequestMapping(path = "/update/field/{id}", method = RequestMethod.PATCH)
//     public ResponseEntity<?> updateChannel(@PathVariable(value = "id") String id,
//             @RequestBody @Valid YoutubeChannelRequest request) {
//         return super.updateWidget(Long.parseLong(id), request, this.youtubeChannelService);
//     }

//     @RequestMapping(path = "/update/{id}")
//     public ResponseEntity<?> UpdateDataWidget(@PathVariable(value = "id") String id) {
//         return super.updateData(Long.parseLong(id), this.youtubeChannelService);
//     }
// }
