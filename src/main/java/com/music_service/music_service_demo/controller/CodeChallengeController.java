package com.music_service.music_service_demo.controller;

import com.music_service.music_service_demo.model.Track;
import com.music_service.music_service_demo.service.SpotifyTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/codechallenge")
public class CodeChallengeController {

    private final SpotifyTrackService spotifyTrackService;

    @GetMapping(value = "/getTrackMetadata", produces = "application/json")
    public ResponseEntity<Track> getTrackMetadata(@RequestParam String isrc){
        return ResponseEntity.ok(new Track());
    }
}
