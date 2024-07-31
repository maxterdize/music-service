package com.music_service.music_service_demo.controller;

import com.music_service.music_service_demo.model.Track;
import com.music_service.music_service_demo.rest.TrackResponse;
import com.music_service.music_service_demo.service.SpotifyTrackService;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;

@RequiredArgsConstructor
@RestController("/codechallenge")
public class CodeChallengeController {

    private final SpotifyTrackService spotifyTrackService;

    @GetMapping(value = "/getTrackMetadata", produces = "application/json")
    public ResponseEntity<TrackResponse> getTrackMetadataByIsrc(@RequestParam String isrc) {
        return  ResponseEntity.ok(spotifyTrackService.getTrackByIsrc(isrc));
    }

    @GetMapping(value = "/getTrackMetadata/{trackId}", produces = "application/json")
    public ResponseEntity<TrackResponse> getTrackMetadataById(@PathVariable String trackId){

        return ResponseEntity.ok(spotifyTrackService.getTrackById(trackId));
    }

    @PostMapping(value = "/updateTrack", produces = "application/json")
    public ResponseEntity updateTrack(@RequestBody Track track){
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
