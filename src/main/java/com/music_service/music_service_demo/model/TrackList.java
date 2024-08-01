package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ElementCollection;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TrackList {

    private String href;
    private Integer limit;
    private Integer offset;
    private Integer total;
    @ElementCollection
    List<Track> items;


    @JsonCreator
    public TrackList(@JsonProperty("href")String href,
                     @JsonProperty("limit")Integer limit,
                     @JsonProperty("offset")Integer offset,
                     @JsonProperty("total")Integer total,
                     @JsonProperty("items")List<Track> items) {
        this.href = href;
        this.limit = limit;
        this.offset = offset;
        this.total = total;
        this.items = items;
    }
}
