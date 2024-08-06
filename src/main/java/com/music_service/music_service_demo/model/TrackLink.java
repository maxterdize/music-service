// TrackLink.java
package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music_service.music_service_demo.enums.ModelObjectType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.HashMap;
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
    private Long primary_key;

    // Removed the second @Id annotation to ensure a single primary key
    @Column(name = "secondary_id")
    private String id;

    @Column
    @JsonProperty("is_playable")
    private Boolean isPlayable;

    @Builder.Default
    @ElementCollection
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls = new HashMap<>();

    @Column(name = "track_link_href")
    @JsonProperty("href")
    private String href;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    @JsonProperty("type")
    private ModelObjectType type;

    @Column
    @JsonProperty("uri")
    private String uri;

    @OneToOne(mappedBy = "linkedFrom")
    private Track track;
}