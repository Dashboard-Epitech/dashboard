package com.dashboard.api.Entity;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpotifyWidget extends Widget {
    private String category;
    private String type;
    private String size;
    private String trackId;
    private String playlistId;
}
