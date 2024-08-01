package com.music_service.music_service_demo.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ImageResponse {

    private String url;
    private Integer height;
    private Integer width;
}
