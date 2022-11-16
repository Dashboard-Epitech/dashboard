package com.dashboard.api.Model.Request.widget.weather;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSpotifyWidget {
    private Long dashboardId;
    private String type;
    private String size;
    
    @Nullable
    private String trackId;

    @Nullable
    private String playlistId;
}
