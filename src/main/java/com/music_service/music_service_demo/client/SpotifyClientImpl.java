package com.music_service.music_service_demo.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music_service.music_service_demo.enums.ModelObjectType;
import com.music_service.music_service_demo.enums.TrackSearchParams;
import com.music_service.music_service_demo.model.*;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpotifyClientImpl implements SpotifyClient{

    private final RestTemplateBuilder restTemplateBuilder;

    private String GET_TRACK_PATH = "/v1/tracks/";
    private String SEARCH_TRACK_PATH = "/v1/search";
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Track getTrackById(String trackId) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(GET_TRACK_PATH + trackId);

        ResponseEntity<Track> trackResponseEntity = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Track.class);

        return trackResponseEntity.getBody();
    }

    @Override
    public TrackList getTrackByIsrc(String isrc) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(SEARCH_TRACK_PATH);
        uriComponentsBuilder.queryParam(TrackSearchParams.Q.getKey(), isrc);
        uriComponentsBuilder.queryParam(TrackSearchParams.TYPE.getKey(), ModelObjectType.TRACK.type);
        String uri = uriComponentsBuilder.toUriString();

        TrackList trackList = null;
        try {
            ResponseEntity<Map> mapResponse = restTemplate.getForEntity(uriComponentsBuilder.toUriString(), Map.class);
            Map<String, Object> trackMap = mapResponse.getBody();
            trackList = new TrackList();
            trackMap = (Map) trackMap.get("tracks");
            trackList.setHref((String) trackMap.get("href"));
            trackList.setLimit((Integer) trackMap.get("limit"));
            trackList.setOffset((Integer) trackMap.get("offset"));
            trackList.setTotal((Integer) trackMap.get("total"));
            List<Track> tracks = new ArrayList<>();
            List<Map<String, Object>> items = (List<Map<String, Object>>) trackMap.get("items");
            for (Map<String, Object> item : items) {
                tracks.add(Track.builder()
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
                        .build());
            }
            trackList.setItems(tracks);
        } catch (Exception e) {
            return null;
        }

        return trackList;
    }

    private Set<Artist> getArtistsFromResponse(Map<String, Object> item) {
        List<Map<String, Object>> artistItems = (List<Map<String, Object>>) item.get("artists");
        return artistItems.stream()
                .map(object -> objectMapper.convertValue(object, Artist.class))
                .collect(Collectors.toSet());
    }

    private Set<CountryCode> getAvailableMarketsFromResponse(Map<String, Object> item) {
        List<String> countryCodes = null;

        try {
            countryCodes = (List<String>) item.get("available_markets");
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException("The 'available_markets' object could not be cast to a List of Strings.", ex);
        }

        if (countryCodes == null) {
            throw new IllegalArgumentException("No 'available_markets' key found in the map.");
        }

        try {
            return countryCodes.stream()
                    .map(CountryCode::valueOf)
                    .collect(Collectors.toSet());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid CountryCode value encountered.", e);
        }
    }

    private Map<String, String> getExternalIdsFromResponse(Map<String, Object> item) {
        if(item.get("external_ids") instanceof List){
            List<Map<String, Object>> externalIds = (List<Map<String, Object>>) item.get("external_ids");
            if(externalIds != null) {
                return externalIds.stream()
                        .flatMap(map -> map.entrySet().stream())
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString(), (v1, v2) -> v1));
            }
        }
        return Collections.emptyMap();
    }

    private Map<String, String> getExternalUrlsFromResponse(Map<String, Object> item) {
        try {
            Map<String, Object> externalUrls = (Map<String, Object>) item.get("external_urls");
            return externalUrls.entrySet().stream()
                    .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString()));
        } catch (ClassCastException e) {
            // Log error and handle exception appropriately
            e.printStackTrace();
            return new HashMap<>();
        }
    }


}
