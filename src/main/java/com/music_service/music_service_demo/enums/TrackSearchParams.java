package com.music_service.music_service_demo.enums;

import java.util.HashMap;
import java.util.Map;

public enum TrackSearchParams {
    Q("q"),
    TYPE("type"),
    MARKET("market"),
    LIMIT("limit"),
    OFFSET("offset"),
    INCLUDE_EXTERNAL("include_external");

    private final String key;
    private static final Map<String, TrackSearchParams> BY_VALUE = new HashMap<>();

    TrackSearchParams(String key) {
        this.key = key;
    }

    // static initializer
    static {
        for (TrackSearchParams e : values()) {
            BY_VALUE.put(e.getKey(), e);
        }
    }

    public String getKey() {
        return key;
    }

    public static TrackSearchParams getByValue(String value) {
        return BY_VALUE.get(value);
    }
}
