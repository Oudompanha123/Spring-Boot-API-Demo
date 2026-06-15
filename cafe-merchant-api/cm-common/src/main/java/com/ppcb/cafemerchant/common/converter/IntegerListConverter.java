package com.ppcb.cafemerchant.common.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

// Converter to handle List<Integer> to String (comma-separated) and vice-versa
@Converter
public class IntegerListConverter implements AttributeConverter<List<Integer>, String> {

    private static final String SEPARATOR = ",";

    @Override
    public String convertToDatabaseColumn(List<Integer> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null; // or return "" if you prefer empty string
        }
        return attribute.stream()
                .filter(Objects::nonNull) // Filter out null values
                .map(String::valueOf)
                .collect(Collectors.joining(SEPARATOR));
    }

    @Override
    public List<Integer> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new ArrayList<>(); // Return empty list instead of null
        }
        try {
            return Arrays.stream(dbData.split(SEPARATOR))
                    .filter(s -> !s.trim().isEmpty()) // Handle empty strings
                    .map(s -> Integer.valueOf(s.trim())) // Trim whitespace
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Error parsing integer list from database: " + dbData, e);
        }
    }
}
