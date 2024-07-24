package com.music_service.music_service_demo.util;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StringMapConverter implements AttributeConverter<Map<String, String>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, String> attribute) {
        return attribute.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .reduce((a, b) -> a + "," + b)
                .orElse("");
    }

    @Override
    public Map<String, String> convertToEntityAttribute(String dbData) {
        Map<String, String> map = new HashMap<>();
        Arrays.stream(dbData.split(","))
                .forEach(entry -> {
                    String[] keyValue = entry.split(":");
                    map.put(keyValue[0], keyValue[1]);
                });
        return map;
    }
}
