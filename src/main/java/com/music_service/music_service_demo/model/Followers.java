package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Followers {

    @Column(name = "followers_href")
    @JsonProperty("href")
    private String href;
    @Column
    @JsonProperty("total")
    private Integer total;
}
