package com.dashboard.api.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Service.SpotifyWidgetService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/widget/spotify")
@CrossOrigin(originPatterns = "http://localhost:*")
public class SpotifyWidgetController {
    private SpotifyWidgetService spotifyWidgetService;
}
