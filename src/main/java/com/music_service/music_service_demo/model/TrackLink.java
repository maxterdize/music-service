package com.music_service.music_service_demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.music_service.music_service_demo.enums.ModelObjectType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "track_link")
public class TrackLink {

    @Id
    private String id;
    @Column
    private Boolean isPlayable;
    private ExternalUrl externalUrls;
    @Column(name = "track_link_href")
    private String href;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
    private ModelObjectType type;
    @Column
    private String uri;

}
