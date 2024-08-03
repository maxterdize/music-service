// Restriction.java
package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Embeddable
public class Restriction {

    @Column
    @JsonProperty("reason")
    private String reason;
}