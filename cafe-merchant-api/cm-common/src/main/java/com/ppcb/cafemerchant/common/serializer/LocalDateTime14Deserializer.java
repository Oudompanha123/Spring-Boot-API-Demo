package com.ppcb.cafemerchant.common.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTime14Deserializer extends StdDeserializer<LocalDateTime> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    protected LocalDateTime14Deserializer() {
        super(LocalDateTime.class);
    }

    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String dateAsString = jsonParser.getText();
        String fieldName = jsonParser.getParsingContext().getCurrentName();

        try {

            if(StringUtils.hasLength(dateAsString)){
                return LocalDateTime.parse(dateAsString, FORMATTER);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Invalid date format for field '%s'. Value '%s' does not match expected format: %s",
                    fieldName, dateAsString, "yyyyMMddHHmmss (e.g. 2050010100000)"));
        }
        return null;
    }
}