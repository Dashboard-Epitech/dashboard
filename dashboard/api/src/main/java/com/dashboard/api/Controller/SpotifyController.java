package com.dashboard.api.Controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.Service.SpotifyService;

import lombok.RequiredArgsConstructor;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/spotify")
public class SpotifyController {

    private final SpotifyService spotifyService;

    @GetMapping("/login")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<?> spotifyLogin() {
        try {
            return ResponseEntity.ok().body(spotifyService.getAuthCode());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("token")
    @CrossOrigin(originPatterns = "*")
    public ResponseEntity<?> spotifyToken(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        SpotifyApi spotifyApi = spotifyService.geSpotifyApi();
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(userCode).build();

        try {
            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            // response.sendRedirect("http://localhost:3000");
            return ResponseEntity.ok().body(spotifyApi.getAccessToken());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    // @GetMapping("playlists")
    // public ResponseEntity<?> getUserPlaylists() {
        
    // }
}
