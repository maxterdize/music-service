package com.music_service.music_service_demo.controller;

import com.music_service.music_service_demo.model.Track;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/codechallenge")
public class CodeChallengeController {

    @GetMapping(value = "/getTrackMetadata", produces = "application/json")
    public ResponseEntity<Track> getTrackMetadata(@RequestParam String isrc){
        return ResponseEntity.ok(new Track());
    }
}
