package com.music_service.music_service_demo.model;

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
    private String href;
    @Column
    private Integer total;
}
