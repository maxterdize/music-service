package com.music_service.music_service_demo.repository;

import com.music_service.music_service_demo.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotifyTrackRepository extends JpaRepository<Track, String> {

    Track findTrackById(String id);
    boolean existsById(String id);
}
