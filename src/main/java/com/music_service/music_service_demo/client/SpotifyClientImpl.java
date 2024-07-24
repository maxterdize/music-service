package com.music_service.music_service_demo.client;

import com.music_service.music_service_demo.model.Track;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class SpotifyClientImpl implements SpotifyClient{

    private final RestTemplateBuilder restTemplateBuilder;

    private String GET_TRACK_PATH = "/v1/tracks";

    @Override
    public Track getTrackById(String trackId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String path = GET_TRACK_PATH + "/" + trackId;

        ResponseEntity<Track> trackResponseEntity = restTemplate.getForEntity(path, Track.class);

        return trackResponseEntity.getBody();
    }
}
