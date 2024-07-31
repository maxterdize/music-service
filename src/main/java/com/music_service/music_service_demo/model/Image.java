package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class Image {

    @Column
    @JsonProperty("url")
    private String url;
    @Column
    @JsonProperty("height")
    private Integer height;
    @Column
    @JsonProperty("width")
    private Integer width;
}
