package com.music_service.music_service_demo.repository;

import com.music_service.music_service_demo.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotifyArtistRepository  extends JpaRepository<Artist, String> {
}
