package com.dashboard.api.Service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

@Service
@NoArgsConstructor
public class SpotifyService {
    @Value("${SPOTIFY_API_CLIENT_ID}")
    private String clientId;

    @Value("${SPOTIFY_API_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${SPOTIFY_API_CLIENT_REDIRECT}")
    private String clientRedirect;

    private URI buildRedirectUri() {
        return SpotifyHttpManager.makeUri(clientRedirect);
    }

    private SpotifyApi spotifyApi() {
        return new SpotifyApi.Builder()
        .setClientId(clientId)
        .setClientSecret(clientSecret)
        .setRedirectUri(buildRedirectUri())
        .build();
    } 

    public String getAuthCode() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi().authorizationCodeUri()
                                                                        .scope("user-library-read")
                                                                        .show_dialog(true)
                                                                        .build();
        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    public SpotifyApi geSpotifyApi() {
        return this.spotifyApi();
    }
}
