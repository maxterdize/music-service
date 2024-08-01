package com.music_service.music_service_demo.rest;

import com.music_service.music_service_demo.model.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AlbumResponse {

    private String id;
    private String name;
    private Integer totalTracks;
    private Set<ImageResponse> images;

}
