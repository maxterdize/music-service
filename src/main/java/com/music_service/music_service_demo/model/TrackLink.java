package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music_service.music_service_demo.enums.ModelObjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "track_link")
public class TrackLink {

    @Id
    private String id;
    @Column
    @JsonProperty("is_playable")
    private Boolean isPlayable;
    @ElementCollection
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
    @Column(name = "track_link_href")
    @JsonProperty("href")
    private String href;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    @JsonProperty("type")
    private ModelObjectType type;
    @Column
    @JsonProperty("uri")
    private String uri;

}
