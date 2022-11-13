package com.dashboard.api.Service;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Entity.SpotifyToken;
import com.dashboard.api.Exception.UserNotFoundException;
import com.dashboard.api.Model.Response.TokenResponse;
import com.dashboard.api.Repository.DashboardUserRepository;
import com.dashboard.api.Repository.SpotifyTokenRepository;
import com.neovisionaries.i18n.CountryCode;

import antlr.Token;
import lombok.NoArgsConstructor;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.model_objects.specification.TrackSimplified;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;

@Service
@NoArgsConstructor
public class SpotifyService {
    @Value("${SPOTIFY_API_CLIENT_ID}")
    private String clientId;

    @Value("${SPOTIFY_API_CLIENT_SECRET}")
    private String clientSecret;

    @Value("${SPOTIFY_API_CLIENT_REDIRECT}")
    private String clientRedirect;

    @Autowired
    private DashboardUserRepository userRepository;

    @Autowired
    private SpotifyTokenRepository spotifyTokenRepository;

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

    public SpotifyApi getSpotifyApi() {
        return this.spotifyApi();
    }

    public String setSpotifyToken(Long userId, String token, String refresh) throws UserNotFoundException, Exception {
        try {
            Optional<DashboardUser> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new UserNotFoundException();
            }
    
            SpotifyToken spotifyToken = new SpotifyToken();
            spotifyToken.setToken(token);
            spotifyToken.setRefresh(refresh);
            spotifyToken.setExpires(LocalDateTime.now().plusHours(1));
    
            spotifyToken = spotifyTokenRepository.save(spotifyToken);
            userRepository.save(user.get().setSpotifyToken(spotifyToken));
    
            return spotifyToken.getToken();
        } catch (Exception ex) {
            throw ex;
        }

    }

    public TokenResponse getSpotifyToken(Long userId) throws UserNotFoundException {
        Optional<DashboardUser> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        SpotifyToken spotifyToken = user.get().getSpotifyToken();
        boolean isTokenExpired = LocalDateTime.now().isAfter(spotifyToken.getExpires());
        String token = spotifyToken.getToken();

        return new TokenResponse(token, isTokenExpired);
    }

    public TokenResponse refreshSpotifyToken(Long userId) throws UserNotFoundException, Exception {
        try {
            Optional<DashboardUser> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new UserNotFoundException();
            }
    
            SpotifyToken currentToken = user.get().getSpotifyToken();
            SpotifyApi spotifyApi = getSpotifyApi();
            AuthorizationCodeRefreshRequest authorizationCodeRefresh = spotifyApi.authorizationCodeRefresh(clientId, clientSecret, currentToken.getRefresh()).build();
            AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefresh.execute();

            currentToken.setToken(authorizationCodeCredentials.getAccessToken());
            currentToken.setExpires(LocalDateTime.now().plusHours(1));

            SpotifyToken newToken = spotifyTokenRepository.save(currentToken);
            userRepository.save(user.get().setSpotifyToken(newToken));

            return new TokenResponse(newToken.getToken(), false);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Paging<PlaylistSimplified> getPlaylists(Long userId) throws UserNotFoundException, Exception {
        try {
            Optional<DashboardUser> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new UserNotFoundException();
            }
    
            SpotifyApi spotifyApi = getSpotifyApi();
            spotifyApi.setAccessToken(user.get().getSpotifyToken().getToken());
    
            GetListOfCurrentUsersPlaylistsRequest getListOfCurrentUsersPlaylistsRequest = spotifyApi.getListOfCurrentUsersPlaylists().build();
            Paging<PlaylistSimplified> playlistPaging = getListOfCurrentUsersPlaylistsRequest.execute();
    
            return playlistPaging;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Paging<PlaylistTrack> getTracks(Long userId, String playlistId) throws UserNotFoundException, Exception {
        try {
            Optional<DashboardUser> user = userRepository.findById(userId);
            if (!user.isPresent()) {
                throw new UserNotFoundException();
            }
    
            SpotifyApi spotifyApi = getSpotifyApi();
            spotifyApi.setAccessToken(user.get().getSpotifyToken().getToken());

            Playlist playlist = spotifyApi.getPlaylist(playlistId).build().execute();
            Paging<PlaylistTrack> playlistTracks =  playlist.getTracks();

            return playlistTracks;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Track getEminemTrack(Long userId) throws UserNotFoundException, Exception {
        Optional<DashboardUser> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        SpotifyApi spotifyApi = getSpotifyApi();
        spotifyApi.setAccessToken(user.get().getSpotifyToken().getToken());

        Track[] eminemTrack = spotifyApi.getArtistsTopTracks("7dGJo4pcD2V6oG8kP0tJRR", CountryCode.FR).build().execute();

        Random r = new Random();
        Track randomTrack = eminemTrack[r.nextInt(eminemTrack.length + 1)];

        return randomTrack;
    }
}
