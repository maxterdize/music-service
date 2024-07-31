package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music_service.music_service_demo.enums.AlbumType;
import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "album")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private BigInteger primary_key;

    @JsonProperty("id")
    @Column(name = "secondary_id")
    private String id;
    @Column
    @JsonProperty("album_type")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private AlbumType albumType;
    @Column
    @JsonProperty("total_tracks")
    private Integer totalTracks;
    @ElementCollection
    @JsonProperty("available_markets")
    private Set<CountryCode> availableMarkets;
    @ElementCollection
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
    @Column(name = "album_href")
    @JsonProperty("href")
    private String href;
    @ElementCollection
    @JsonProperty("images")
    private Set<Image> images;
    @Column
    @JsonProperty("name")
    private String name;
    @Column
    @JsonProperty("release_date")
    private String releaseDate;
    @Column
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    @Embedded
    @JsonProperty("restrictions")
    private Restriction restrictions;
    @Column
    @JsonProperty("uri")
    private String uri;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id", referencedColumnName = "id"))
    @JsonProperty("artists")
    private Set<Artist> artists;

    @OneToMany(mappedBy = "album")
    private Set<Track> tracks;




}
