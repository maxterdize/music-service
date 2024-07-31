package com.music_service.music_service_demo.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TrackResponse {

    private String name;
    private String artistName;
    private String albumName;
    private String albumId;
    private Boolean isExplicit;
    private Integer playbackSeconds;
}
