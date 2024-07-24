package com.music_service.music_service_demo.model;

import com.music_service.music_service_demo.util.StringMapConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Embeddable
public class ExternalUrl {

    @Convert(converter = StringMapConverter.class)
    private Map<String, String> externalUrls;
}
