package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.music_service.music_service_demo.enums.ModelObjectType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "artist")
@SecondaryTable(name = "followers", pkJoinColumns = @PrimaryKeyJoinColumn(name = "artist_id"))
public class Artist {

    private static final String JOIN_TABLE_NAME = "album_artist";
    private static final String JOIN_COLUMN_NAME = "artist_id";
    private static final String INVERSE_JOIN_COLUMN_NAME = "album_id";

    @Id
    private String id;
    @Column(name = "artist_href")
    private String href;
    private String name;
    private String uri;
    private Integer popularity;
    @ElementCollection
    private Set<Image> images;
    private ExternalUrl externalUrls;
    @Embedded
    private Followers followers;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private ModelObjectType type;
    @ElementCollection
    private Set<String> genres;
    @ManyToMany
    @JoinTable(name = JOIN_TABLE_NAME,
            joinColumns = @JoinColumn(name = JOIN_COLUMN_NAME),
            inverseJoinColumns = @JoinColumn(name = INVERSE_JOIN_COLUMN_NAME))
    private Set<Album> albums;



}
