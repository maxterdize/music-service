package com.music_service.music_service_demo.model;

import com.music_service.music_service_demo.enums.AlbumType;
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
@Table(name = "album")
@SecondaryTable(name = "artist", pkJoinColumns = @PrimaryKeyJoinColumn(name = "album_id"))
public class Album {

    private static final String JOIN_TABLE_NAME = "album_artist";
    private static final String JOIN_COLUMN_NAME = "album_id";
    private static final String INVERSE_JOIN_COLUMN_NAME = "artist_id";

    @Id
    private String id;
    @Column
    private AlbumType albumType;
    @Column
    private Integer totalTracks;
    @ElementCollection
    private Set<CountryCode> availableMarkets;
    private ExternalUrl externalUrls;
    @Column(name = "album_href")
    private String href;
    @ElementCollection
    private Set<Image> images;
    @Column
    private String name;
    @Column
    private String releaseDate;
    @Column
    private String releaseDatePrecision;
    @Embedded
    private Restriction restrictions;
    @Column
    private String uri;
    @ManyToMany
    @JoinTable(name = JOIN_TABLE_NAME,
            joinColumns = @JoinColumn(name = JOIN_COLUMN_NAME),
            inverseJoinColumns = @JoinColumn(name = INVERSE_JOIN_COLUMN_NAME))
    private Set<Artist> artists;




}
