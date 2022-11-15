package com.dashboard.api.Controller;

import java.io.IOException;
import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Entity.SpotifyToken;
import com.dashboard.api.Model.Request.BindSpotifyTokenRequest;
import com.dashboard.api.Security.oauth2.user.DashboardUserPrincipal;
import com.dashboard.api.Service.SpotifyService;
import com.dashboard.api.Service.UserService;

import lombok.RequiredArgsConstructor;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/spotify")
@CrossOrigin(originPatterns = "http://localhost:*")
public class SpotifyController {

    private final SpotifyService spotifyService;
    private final UserService userService;

    @GetMapping("login")
    public ResponseEntity<?> spotifyLogin() {
        try {
            return ResponseEntity.ok().body(spotifyService.getAuthCode());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("token")
    public ResponseEntity<?> spotifyToken(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        try {
            SpotifyApi spotifyApi = spotifyService.getSpotifyApi();
            AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(userCode).build();
            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();


            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "http://localhost:3000/oauth2/redirect/spotify?token=" + spotifyApi.getAccessToken() + "&refresh=" + spotifyApi.getRefreshToken()).build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @PostMapping("token/bind")
    public ResponseEntity<?> bindSpotifyToken(@AuthenticationPrincipal DashboardUserPrincipal user, @RequestBody BindSpotifyTokenRequest request) {
        try {
            String token = spotifyService.setSpotifyToken(user.getId(), request.getToken(), request.getRefresh());

            return ResponseEntity.ok().body("Successfully bound spotify token to user " + user.getId());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("usertoken") 
    public ResponseEntity<?> userToken(@AuthenticationPrincipal DashboardUserPrincipal user) {
        try {
            return ResponseEntity.ok().body(spotifyService.getSpotifyToken(user.getId()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getClass() + " : " + ex.getMessage());
        }
    }

    @GetMapping("refresh")
    public ResponseEntity<?> refreshSpotifyToken(@AuthenticationPrincipal DashboardUserPrincipal user) {
        try {
            return ResponseEntity.ok().body(spotifyService.refreshSpotifyToken(user.getId()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getClass() + " : " + ex.getMessage());
        }
    }

    @GetMapping("playlists")
    public ResponseEntity<?> getUserPlaylists(@AuthenticationPrincipal DashboardUserPrincipal user) {
        try {
            return ResponseEntity.ok().body(spotifyService.getPlaylists(user.getId()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("playlists/tracks/{playlistId}")
    public ResponseEntity<?> getTracks(@AuthenticationPrincipal DashboardUserPrincipal user, @PathVariable("playlistId") String playlistId) {
        try {
            return ResponseEntity.ok().body(spotifyService.getTracks(user.getId(), playlistId));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }

    @GetMapping("track/eminem")
    public ResponseEntity<?> getArtistTrack(@AuthenticationPrincipal DashboardUserPrincipal user) {
        try {
            return ResponseEntity.ok().body(spotifyService.getEminemTrack(user.getId()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}
