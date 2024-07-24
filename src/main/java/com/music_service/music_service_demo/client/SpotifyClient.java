package com.music_service.music_service_demo.client;

import com.music_service.music_service_demo.model.Track;

public interface SpotifyClient {

    Track getTrackById(String trackId);
}
