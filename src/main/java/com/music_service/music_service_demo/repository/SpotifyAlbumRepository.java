package com.music_service.music_service_demo.repository;

import com.music_service.music_service_demo.model.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotifyAlbumRepository extends JpaRepository<Album, String> {

    Album getAlbumById(String secondaryId);
}
