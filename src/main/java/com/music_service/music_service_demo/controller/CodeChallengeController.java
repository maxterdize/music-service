package com.music_service.music_service_demo.controller;

import com.music_service.music_service_demo.rest.AlbumResponse;
import com.music_service.music_service_demo.rest.ImageResponse;
import com.music_service.music_service_demo.rest.TrackResponse;
import com.music_service.music_service_demo.service.SpotifyAlbumService;
import com.music_service.music_service_demo.service.SpotifyTrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController("/codechallenge")
public class CodeChallengeController {

    private final SpotifyTrackService spotifyTrackService;
    private final SpotifyAlbumService spotifyAlbumService;

    @PostMapping(value = "/createTrack", produces = "application/json")
    public ResponseEntity<TrackResponse> createTrack(@RequestParam String isrc) {
        return new ResponseEntity<>(spotifyTrackService.createTrack(isrc), HttpStatus.CREATED);
    }

    @GetMapping(value = "/getTrackMetadata", produces = "application/json")
    public ResponseEntity<TrackResponse> getTrackMetadataByIsrc(@RequestParam String isrc) {
        return  ResponseEntity.ok(spotifyTrackService.getTrackByIsrc(isrc));
    }

    @GetMapping(value = "/getTrackMetadata/{trackId}", produces = "application/json")
    public ResponseEntity<TrackResponse> getTrackMetadataById(@PathVariable String trackId){

        return ResponseEntity.ok(spotifyTrackService.getTrackById(trackId));
    }

    @GetMapping(value = "/albums/{albumId}", produces = "application/json")
    public ResponseEntity<AlbumResponse> getAlbumById(@PathVariable String albumId){
        return ResponseEntity.ok(spotifyAlbumService.getAlbumByAlbumId(albumId));
    }

    @GetMapping(value = "/getCover", produces = "application/json")
    public ResponseEntity<ImageResponse> getCover(@RequestParam String isrc) {
        return new ResponseEntity<>(spotifyAlbumService.getCoverByIsrc(isrc), HttpStatus.OK);
    }
}
