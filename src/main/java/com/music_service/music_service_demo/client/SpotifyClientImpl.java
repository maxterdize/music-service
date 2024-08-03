package com.music_service.music_service_demo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_service.music_service_demo.enums.ModelObjectType;
import com.music_service.music_service_demo.enums.TrackSearchParams;
import com.music_service.music_service_demo.model.*;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpotifyClientImpl implements SpotifyClient {

    private final RestTemplateBuilder restTemplateBuilder;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final String GET_TRACK_PATH = "/v1/tracks/";
    private static final String SEARCH_TRACK_PATH = "/v1/search";

    @Override
    public Track getTrackById(String trackId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String uri = UriComponentsBuilder.fromPath(GET_TRACK_PATH + trackId).toUriString();
        return Optional.ofNullable(restTemplate.getForEntity(uri, Track.class).getBody()).orElseThrow();
    }

    @Override
    public TrackList getTrackByIsrc(String isrc) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String uri = UriComponentsBuilder.fromPath(SEARCH_TRACK_PATH)
                .queryParam(TrackSearchParams.Q.getKey(), isrc)
                .queryParam(TrackSearchParams.TYPE.getKey(), ModelObjectType.TRACK.type)
                .toUriString();

        try {
            Map<String, Object> trackMap = restTemplate.getForEntity(uri, Map.class).getBody();
            return buildTrackList(trackMap);
        } catch (Exception e) {
            return null;
        }
    }

    private TrackList buildTrackList(Map<String, Object> trackMap) {
        TrackList trackList = new TrackList();
        Map<String, Object> tracks = (Map<String, Object>) trackMap.get("tracks");
        trackList.setHref((String) tracks.get("href"));
        trackList.setLimit((Integer) tracks.get("limit"));
        trackList.setOffset((Integer) tracks.get("offset"));
        trackList.setTotal((Integer) tracks.get("total"));

        List<Track> trackItems = ((List<Map<String, Object>>) tracks.get("items")).stream()
                .map(this::buildTrack)
                .collect(Collectors.toList());
        trackList.setItems(trackItems);

        return trackList;
    }

    private Track buildTrack(Map<String, Object> item) {
        return Track.builder()
                .id(item.get("id").toString())
                .name(item.get("name").toString())
                .album(objectMapper.convertValue(item.get("album"), Album.class))
                .artists(getArtistsFromResponse(item))
                .availableMarkets(getAvailableMarketsFromResponse(item))
                .discNumber(Integer.parseInt(item.get("disc_number").toString()))
                .durationMs(Integer.parseInt(item.get("duration_ms").toString()))
                .explicit(Boolean.parseBoolean(item.get("explicit").toString()))
                .externalIds(getExternalIdsFromResponse(item))
                .externalUrls(getExternalUrlsFromResponse(item))
                .href(item.get("href").toString())
                .linkedFrom(objectMapper.convertValue(item.get("linked_from"), TrackLink.class))
                .restrictions(objectMapper.convertValue(item.get("restrictions"), Restriction.class))
                .build();
    }

    private Set<Artist> getArtistsFromResponse(Map<String, Object> item) {
        return ((List<Map<String, Object>>) item.get("artists")).stream()
                .map(artist -> objectMapper.convertValue(artist, Artist.class))
                .collect(Collectors.toSet());
    }

    private Set<CountryCode> getAvailableMarketsFromResponse(Map<String, Object> item) {
        List<String> countryCodes = Optional.ofNullable((List<String>) item.get("available_markets"))
                .orElseThrow(() -> new IllegalArgumentException("No 'available_markets' key found in the map."));
        return countryCodes.stream()
                .map(CountryCode::valueOf)
                .collect(Collectors.toSet());
    }

    private Map<String, String> getExternalIdsFromResponse(Map<String, Object> item) {
        return Optional.ofNullable((Map<String, Object>) item.get("external_ids"))
                .map(externalIds -> externalIds.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString())))
                .orElse(Collections.emptyMap());
    }

    private Map<String, String> getExternalUrlsFromResponse(Map<String, Object> item) {
        return Optional.ofNullable((Map<String, Object>) item.get("external_urls"))
                .map(externalUrls -> externalUrls.entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString())))
                .orElse(Collections.emptyMap());
    }
}