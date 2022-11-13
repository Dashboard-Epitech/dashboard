// package com.dashboard.api.Controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;

// import com.dashboard.api.Service.YoutubeService;

// @Controller
// @RequestMapping("widget/youtube/video")
// public class YoutubeVideoController extends WidgetContoller {

// @Autowired
// YoutubeService youtubeService;

// @RequestMapping(path = "create", method = RequestMethod.POST)
// public ResponseEntity<?> createVideo() {
// return super.createWidget(youtubeService);
// }

// @RequestMapping(path = "update/{id}", method = RequestMethod.POST)
// public ResponseEntity<?> updateVideo(@PathVariable(value = "id") String id,
// @RequestBody String body) {
// return super.updateWidget(Long.parseLong(id), body, youtubeService);
// }

// @RequestMapping(path = "/updateData/{id}")
// public ResponseEntity<?> UpdateDataVideo(@PathVariable(value = "id") String
// id) {
// return super.updateData(Long.parseLong(id), youtubeService);
// }
// }
