package com.dashboard.api.Exception;

public class SpotifyTokenExpiredException extends Exception {
    public SpotifyTokenExpiredException() {
        super("Spotify token is expired.");
    }
}
