package com.music_service.music_service_demo.client;

import com.music_service.music_service_demo.model.Track;
import com.music_service.music_service_demo.model.TrackList;

public interface SpotifyClient {

    Track getTrackById(String trackId);
    TrackList getTrackByIsrc(String isrc);
}
