package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music_service.music_service_demo.enums.AlbumType;
import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "album")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long primary_key;

    @JsonProperty("id")
    @Column(name = "secondary_id")
    private String id;

    @Column
    @Builder.Default
    private String isrc = "";

    @Column
    @JsonProperty("album_type")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private AlbumType albumType;

    @Column
    @JsonProperty("total_tracks")
    private Integer totalTracks;

    @Column
    @Enumerated
    @Builder.Default
    @ElementCollection(targetClass = CountryCode.class)
    @JsonProperty("available_markets")
    private Set<CountryCode> availableMarkets = new HashSet<>();

    @Builder.Default
    @ElementCollection
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls = new HashMap<>();

    @Column(name = "album_href")
    @JsonProperty("href")
    private String href;

    @Builder.Default
    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    @JsonProperty("images")
    private Set<Image> images = new HashSet<>();

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

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "album_artist",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    @JsonProperty("artists")
    private Set<Artist> artists = new HashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "album")
    private Set<Track> tracks = new HashSet<>();

    public void addArtist(Artist artist) {
        this.artists.add(artist);
        artist.getAlbums().add(this);
    }

    public void removeArtist(Artist artist) {
        this.artists.remove(artist);
        artist.getAlbums().remove(this);
    }
}