package com.music_service.music_service_demo.service;

import com.music_service.music_service_demo.model.Album;
import com.music_service.music_service_demo.model.Image;
import com.music_service.music_service_demo.repository.SpotifyAlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpotifyAlbumService {

    private final SpotifyAlbumRepository spotifyAlbumRepository;

    public Album getAlbumImageByAlbumId(String albumId) {
       Album album = spotifyAlbumRepository.getAlbumById(albumId);
        Set<Image> images = album.getImages().stream().map((image -> {
           image.setHeight(500);
           image.setWidth(500);
           return image;
       })).collect(Collectors.toSet());

       album.setImages(images);

       return album;
    }


}
