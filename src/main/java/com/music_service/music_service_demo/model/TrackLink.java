package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music_service.music_service_demo.enums.ModelObjectType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "track_link")
public class TrackLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private BigInteger primary_key;

    @Id
    @Column(name = "secondary_id")
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
