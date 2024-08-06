package com.music_service.music_service_demo.service;

import com.music_service.music_service_demo.exceptions.TrackNotFoundException;
import com.music_service.music_service_demo.model.Album;
import com.music_service.music_service_demo.model.Image;
import com.music_service.music_service_demo.repository.SpotifyAlbumRepository;
import com.music_service.music_service_demo.rest.AlbumResponse;
import com.music_service.music_service_demo.rest.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpotifyAlbumService {

    private final SpotifyAlbumRepository spotifyAlbumRepository;
    private static final int STANDARD_HEIGHT = 500;
    private static final int STANDARD_WIDTH = 500;

    public AlbumResponse getAlbumByAlbumId(String albumId) {
        Album album = spotifyAlbumRepository.getAlbumById(albumId);
        Set<ImageResponse> images = album.getImages().stream().map(image ->
                ImageResponse.builder()
                        .url(image.getUrl())
                        .height(STANDARD_HEIGHT)
                        .width(STANDARD_WIDTH)
                        .build()
        ).collect(Collectors.toSet());

        return AlbumResponse.builder()
                .id(album.getId())
                .name(album.getName())
                .totalTracks(album.getTotalTracks())
                .images(images)
                .build();
    }

    public ImageResponse getCoverByIsrc(String isrc) {
        Image image = spotifyAlbumRepository.getAlbumByIsrc(isrc).getImages().stream().findFirst().orElseThrow(() ->
                new TrackNotFoundException("Error getting track by Isrc: " + isrc));

        return ImageResponse.builder()
                .url(image.getUrl())
                .height(500)
                .width(500)
                .build();
    }


}
