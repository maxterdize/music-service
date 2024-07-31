package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Restriction {

    @Column
    @JsonProperty("reason")
    private String reason;
}
