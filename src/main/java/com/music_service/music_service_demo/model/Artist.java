package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.music_service.music_service_demo.enums.ModelObjectType;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
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

    @Builder.Default
    @OneToMany(mappedBy = "artist")
    @JsonProperty("images")
    private Set<Image> images = new HashSet<>();

    @Builder.Default
    @ElementCollection
    @JsonProperty("external_urls")
    private Map<String, String> externalUrls = new HashMap<>();

    @Embedded
    @JsonProperty("followers")
    private Followers followers;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    @JsonProperty("type")
    private ModelObjectType type;

    @Builder.Default
    @ElementCollection
    @JsonProperty("genres")
    private Set<String> genres = new HashSet<>();

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "track_artist",
        joinColumns = @JoinColumn(name = "artist_id"),
        inverseJoinColumns = @JoinColumn(name = "track_id"))
    private Set<Track> tracks = new HashSet<>();

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "artist_id"),
                inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> albums = new HashSet<>();

    // Ensure proper handling of circular references
    public void addTrack(Track track) {
        this.tracks.add(track);
        track.getArtists().add(this);
    }

    public void removeTrack(Track track) {
        this.tracks.remove(track);
        track.getArtists().remove(this);
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
        album.getArtists().add(this);
    }

    public void removeAlbum(Album album) {
        this.albums.remove(album);
        album.getArtists().remove(this);
    }
}