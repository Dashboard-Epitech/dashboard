package com.dashboard.api.Model.Request.Auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BindSpotifyTokenRequest {
    String token;
    String refresh;
}
