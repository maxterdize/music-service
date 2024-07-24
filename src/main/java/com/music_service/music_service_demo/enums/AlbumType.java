package com.music_service.music_service_demo.enums;

import java.util.Map;
import java.util.HashMap;

public enum AlbumType {
    ALBUM("album"),
    COMPILATION("compilation"),
    SINGLE("single");

    private final String type;
    private static final Map<String, AlbumType> LOOKUP = new HashMap<>();

    static {
        for (AlbumType albumType : AlbumType.values()) {
            LOOKUP.put(albumType.getType(), albumType);
        }
    }

    AlbumType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static AlbumType fromType(String type) {
        return LOOKUP.get(type);
    }
}
