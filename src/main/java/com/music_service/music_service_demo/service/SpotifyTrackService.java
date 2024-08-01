package com.music_service.music_service_demo.service;

import com.music_service.music_service_demo.client.SpotifyClient;
import com.music_service.music_service_demo.exceptions.RepositoryTransactionException;
import com.music_service.music_service_demo.exceptions.TrackNotFoundException;
import com.music_service.music_service_demo.model.Track;
import com.music_service.music_service_demo.repository.SpotifyAlbumRepository;
import com.music_service.music_service_demo.repository.SpotifyArtistRepository;
import com.music_service.music_service_demo.repository.SpotifyImageRepository;
import com.music_service.music_service_demo.repository.SpotifyTrackRepository;
import com.music_service.music_service_demo.rest.TrackResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SpotifyTrackService {

    private final SpotifyClient spotifyClient;
    private final SpotifyTrackRepository spotifyTrackRepository;
    private final SpotifyAlbumRepository spotifyAlbumRepository;
    private final SpotifyArtistRepository spotifyArtistRepository;
    private final SpotifyImageRepository spotifyImageRepository;

    @Transactional
    public TrackResponse getTrackById(String id) {
        Track track = spotifyClient.getTrackById(id);
        track.getAlbum().getImages().forEach(image -> image.setAlbum(track.getAlbum()));
        if (track == null) {
            throw new TrackNotFoundException("Error getting track by Id: " + id);
        }

        try {
            spotifyArtistRepository.saveAll(track.getArtists());
            spotifyAlbumRepository.save(track.getAlbum());
            spotifyImageRepository.saveAll(track.getAlbum().getImages());
            spotifyTrackRepository.save(track);
        } catch (Exception e) {
            throw new RepositoryTransactionException("Error saving track: " + id, e);
        }

        return TrackResponse.builder()
                .name(track.getName())
                .artistName(track.getArtists().stream().findFirst().get().getName())
                .albumName(track.getAlbum().getName())
                .albumId(track.getAlbum().getId())
                .isExplicit(track.getExplicit())
                .playbackSeconds(track.getDurationMs())
                .build();
    }

    @Transactional
    public TrackResponse getTrackByIsrc(String isrc) {
         Track track = spotifyClient.getTrackByIsrc(isrc).getItems().stream().findFirst().get();
        if (track == null) {
            throw new TrackNotFoundException("Error getting track by Isrc: " + isrc);
        }
        return TrackResponse.builder()
                .name(track.getName())
                .artistName(track.getArtists().stream().findFirst().get().getName())
                .albumName(track.getAlbum().getName())
                .albumId(track.getAlbum().getId())
                .isExplicit(track.getExplicit())
                .playbackSeconds(track.getDurationMs())
                .build();
    }
}
