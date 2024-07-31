package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music_service.music_service_demo.enums.ModelObjectType;
import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="album_id", nullable=true, referencedColumnName = "id")
    private Album album;

    @JsonProperty("name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "track_artist",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id"))
    @JsonProperty("artists")
    private Set<Artist> artists;
    @ElementCollection
    @JsonProperty("available_markets")
    private Set<CountryCode> availableMarkets;
    @Column
    @JsonProperty("disc_number")
    private Integer discNumber;
    @Column
    @JsonProperty("duration_ms")
    private Integer durationMs;
    @Column
    @JsonProperty("explicit")
    private Boolean explicit;
    @ElementCollection
    @JsonProperty("external_ids")
    private Map<String, String> externalIds;
    @ElementCollection
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls;
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
}
