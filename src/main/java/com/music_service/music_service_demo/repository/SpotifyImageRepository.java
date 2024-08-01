package com.music_service.music_service_demo.repository;

import com.music_service.music_service_demo.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface SpotifyImageRepository extends JpaRepository<Image, Long> {

    Set<Image> getAllByAlbum_Id(String albumId);
}
