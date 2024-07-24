package com.music_service.music_service_demo.service;

import com.music_service.music_service_demo.client.SpotifyClient;
import com.music_service.music_service_demo.model.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpotifyTrackService {

    private final SpotifyClient spotifyClient;

    public Track getTrackById(String id) {
        return spotifyClient.getTrackById(id);
    }
}
