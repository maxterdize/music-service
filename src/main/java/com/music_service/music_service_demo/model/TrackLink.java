package com.music_service.music_service_demo.model;

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
    private Boolean isPlayable;
    private ExternalUrl externalUrls;
    @Column(name = "track_link_href")
    private String href;
    private ModelObjectType type;
    private String uri;

}
