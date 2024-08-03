package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Embeddable
public class Followers {

    @Column(name = "followers_href")
    @JsonProperty("href")
    private String href;
    @Column
    @JsonProperty("total")
    private Integer total;
}
