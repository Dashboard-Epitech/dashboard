package com.dashboard.api.Service;

import org.springframework.stereotype.Service;

import com.dashboard.api.Repository.SpotifyWidgetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpotifyWidgetService {
    private final SpotifyWidgetRepository spotifyWidgetRepository;
}
