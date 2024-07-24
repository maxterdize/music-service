package com.music_service.music_service_demo.enums;
import java.util.Map;
import java.util.HashMap;

public enum ModelObjectType {
    ALBUM("album"),
    ARTIST("artist"),
    AUDIO_FEATURES("audio_features"),
    EPISODE("episode"),
    GENRE("genre"),
    PLAYLIST("playlist"),
    SHOW("show"),
    TRACK("track"),
    USER("user");

    private static final Map<String, ModelObjectType> BY_VALUE = new HashMap<>();

    public final String type;

    static {
        for (ModelObjectType mot : values()) {
            BY_VALUE.put(mot.type, mot);
        }
    }

    ModelObjectType(String type) {
        this.type = type;
    }

    public static ModelObjectType valueOfType(String type) {
        return BY_VALUE.get(type);
    }
}