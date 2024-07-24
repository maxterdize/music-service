package com.music_service.music_service_demo.model;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Restriction {

    @Column
    private String reason;
}
