package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music_service.music_service_demo.enums.ModelObjectType;
import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "track")
@SecondaryTable(name = "track_link", pkJoinColumns = @PrimaryKeyJoinColumn(name = "track_id"))
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long primary_key;

    @JsonProperty("id")
    private String id;

    @JsonProperty("album")
    @ManyToOne
    private Album album;

    @JsonProperty("name")
    private String name;

    @Builder.Default
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "track_id"),
                inverseJoinColumns = @JoinColumn(name = "artist_id"))
    @JsonProperty("artists")
    private Set<Artist> artists = new HashSet<>();

    @Builder.Default
    @ElementCollection
    @JsonProperty("available_markets")
    private Set<CountryCode> availableMarkets = new HashSet<>();

    @Column
    @JsonProperty("disc_number")
    private Integer discNumber;

    @Column
    @JsonProperty("duration_ms")
    private Integer durationMs;

    @Column
    @JsonProperty("explicit")
    private Boolean explicit;

    @Builder.Default
    @ElementCollection
    @JsonProperty("external_ids")
    private Map<String, String> externalIds = new HashMap<>();

    @Builder.Default
    @ElementCollection
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls = new HashMap<>();

    @Column(name = "track_href")
    @JsonProperty("href")
    private String href;

    @Column
    @JsonProperty("is_playable")
    private Boolean isPlayable;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonProperty("linked_from")
    private TrackLink linkedFrom;

    @Embedded
    @JsonProperty("restrictions")
    private Restriction restrictions;

    @Column
    @JsonProperty("popularity")
    private Integer popularity;

    @Column
    @JsonProperty("preview_url")
    private String previewUrl;

    @Column
    @JsonProperty("track_number")
    private Integer trackNumber;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    @JsonProperty("type")
    private ModelObjectType type;

    @Column
    @JsonProperty("uri")
    private String uri;

    @Column
    @JsonProperty("is_local")
    private Boolean isLocal;

    public Track(Long primary_key, String id, Album album, String name, Set<Artist> artists, Set<CountryCode> availableMarkets, Integer discNumber, Integer durationMs, Boolean explicit, Map<String, String> externalIds, Map<String, String> externalUrls, String href, Boolean isPlayable, TrackLink linkedFrom, Restriction restrictions, Integer popularity, String previewUrl, Integer trackNumber, ModelObjectType type, String uri, Boolean isLocal) {
        this.primary_key = primary_key;
        this.id = id;
        this.setAlbum(album);
        this.name = name;
        this.artists = artists;
        this.availableMarkets = availableMarkets;
        this.discNumber = discNumber;
        this.durationMs = durationMs;
        this.explicit = explicit;
        this.externalIds = externalIds;
        this.externalUrls = externalUrls;
        this.href = href;
        this.isPlayable = isPlayable;
        this.linkedFrom = linkedFrom;
        this.restrictions = restrictions;
        this.popularity = popularity;
        this.previewUrl = previewUrl;
        this.trackNumber = trackNumber;
        this.type = type;
        this.uri = uri;
        this.isLocal = isLocal;
    }

    public void setAlbum(Album album) {
        this.album = album;
        if (album != null) {
            album.getTracks().add(this);
        }
    }
}