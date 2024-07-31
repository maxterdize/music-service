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
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long primary_key;

    @JsonProperty("id")
    private String id;
    @Column(name = "artist_href")
    @JsonProperty("href")
    private String href;
    @Column
    @JsonProperty("name")
    private String name;
    @Column
    @JsonProperty("uri")
    private String uri;
    @Column
    @JsonProperty("popularity")
    private Integer popularity;
    @ElementCollection
    @JsonProperty("images")
    private Set<Image> images;
    @ElementCollection
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
    @Embedded
    @JsonProperty("followers")
    private Followers followers;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    @JsonProperty("type")
    private ModelObjectType type;
    @ElementCollection
    @JsonProperty("genres")
    private Set<String> genres;


    @ManyToMany(mappedBy = "artists", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Track> tracks;



}
