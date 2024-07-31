package com.music_service.music_service_demo.client;

import com.google.gson.Gson;
import com.music_service.music_service_demo.enums.ModelObjectType;
import com.music_service.music_service_demo.enums.TrackSearchParams;
import com.music_service.music_service_demo.model.Track;
import com.music_service.music_service_demo.model.TrackList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@SpringBootTest
public class SpotifyClientImplIntegrationTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String GET_TRACK_PATH = "/v1/tracks";
    private String SEARCH_TRACK_PATH = "/v1/search";
    private String ISRC_PREFIX = "isrc:";

    private SpotifyClientImpl spotifyClientImpl;
    private MockRestServiceServer server;

    private String TRACK_ID = "11dFghVXANMlKmJXsNCbNl";
    private String TRACK_NAME = "Cut To The Feeling";

    private final Gson gson = new Gson();

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @BeforeEach
    public void setUp() {
        spotifyClientImpl = new SpotifyClientImpl(restTemplateBuilder);
        server = MockRestServiceServer.createServer(new RestTemplate());
    }

    @Test
    public void testGetTrackById() {
        Track mockTrack = Track.builder()
                .id(TRACK_ID)
                .name(TRACK_NAME)
                .build();

        server.expect(requestTo(GET_TRACK_PATH + "/" + TRACK_ID))
                .andRespond(withSuccess(gson.toJson(mockTrack), MediaType.APPLICATION_JSON));

       Track returnedTrack = spotifyClientImpl.getTrackById(TRACK_ID);

       logger.info(returnedTrack.toString());

        assertEquals(mockTrack.getId(), returnedTrack.getId());
        assertEquals(mockTrack.getName(), returnedTrack.getName());
    }

    @Test
    public void testGetTrackByIsrc() {
        String isrc = "USMC18620";
        Track mockTrack = Track.builder()
                .id(TRACK_ID)
                .name(TRACK_NAME)
                .externalIds((new HashMap<String, String>() {{put("isrc", "USMC18620");}}))
                .build();
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromPath(SEARCH_TRACK_PATH);
        uriComponentsBuilder.queryParam(TrackSearchParams.Q.name(), ISRC_PREFIX + isrc);
        uriComponentsBuilder.queryParam(TrackSearchParams.TYPE.name(), ModelObjectType.TRACK.name());

        server.expect(requestTo(uriComponentsBuilder.toUriString()))
                .andRespond(withSuccess(gson.toJson(mockTrack), MediaType.APPLICATION_JSON));

        Track returnedTrack = spotifyClientImpl.getTrackByIsrc("USMC18620").getItems().stream().findFirst().get();

        logger.info(returnedTrack.toString());

        assertEquals(mockTrack.getId(), returnedTrack.getId());
        assertEquals(mockTrack.getName(), returnedTrack.getName());
        assertEquals(mockTrack.getExternalIds().get("isrc"), returnedTrack.getName());
    }

}
