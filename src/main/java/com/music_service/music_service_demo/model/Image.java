package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Image {

    public Image(Long primary_key, String url, Integer height, Integer width, Album album, Artist artist) {
        this.primary_key = primary_key;
        this.url = url;
        this.height = height;
        this.width = width;
        this.setAlbum(album);
        this.setArtist(artist);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long primary_key;

    @Column
    @JsonProperty("url")
    private String url;
    @Column
    @JsonProperty("height")
    private Integer height;
    @Column
    @JsonProperty("width")
    private Integer width;

    @ManyToOne
    private Album album;

    @ManyToOne
    private Artist artist;

    public void setAlbum(Album album) {
        this.album = album;
        album.getImages().add(this);
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
        artist.getImages().add(this);
    }
}
