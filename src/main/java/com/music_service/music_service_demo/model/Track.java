package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.music_service.music_service_demo.enums.ModelObjectType;
import com.neovisionaries.i18n.CountryCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "track")
@SecondaryTable(name = "track_link", pkJoinColumns = @PrimaryKeyJoinColumn(name = "track_id"))
public class Track {

    private static final String ALBUM_ARTIST_JOIN_TABLE_NAME = "album_artist";
    private static final String TRACK_JOIN_COLUMN_NAME = "album_id";
    private static final String ARTIST_INVERSE_JOIN_COLUMN_NAME = "artist_id";

    @Id
    private String id;
    @ManyToOne
    private Album album;
    private String name;

    @ManyToMany
    @JoinTable(name = ALBUM_ARTIST_JOIN_TABLE_NAME,
            joinColumns = @JoinColumn(name = TRACK_JOIN_COLUMN_NAME),
            inverseJoinColumns = @JoinColumn(name = ARTIST_INVERSE_JOIN_COLUMN_NAME))
    private Set<Artist> artists;
    @ElementCollection
    private Set<CountryCode> availableMarkets;
    @Column
    private Integer discNumber;
    @Column
    private Integer durationMs;
    @Column
    private Boolean explicit;
    private ExternalId externalIds;
    private ExternalUrl externalUrls;
    @Column(name = "track_href")
    private String href;
    @Column
    private Boolean isPlayable;
    @OneToOne
    private TrackLink linkedFrom;
    @Embedded
    private Restriction restrictions;
    @Column
    private Integer popularity;
    @Column
    private String previewUrl;
    @Column
    private Integer trackNumber;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private ModelObjectType type;
    @Column
    private String uri;
    @Column
    private Boolean isLocal;
}
