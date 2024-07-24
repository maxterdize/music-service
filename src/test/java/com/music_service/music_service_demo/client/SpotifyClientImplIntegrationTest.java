package com.music_service.music_service_demo.client;

import com.google.gson.Gson;
import com.music_service.music_service_demo.model.Track;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;


@SpringBootTest
public class SpotifyClientImplIntegrationTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String GET_TRACK_PATH = "/v1/tracks";

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

}
