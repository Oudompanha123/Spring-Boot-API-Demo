package com.ppcb.cafemerchant.common.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDate8Deserializer extends StdDeserializer<LocalDate> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    protected LocalDate8Deserializer() {
        super(LocalDate.class);
    }

    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String dateAsString = jsonParser.getText();
        String fieldName = jsonParser.getParsingContext().getCurrentName();

        try {

            if(StringUtils.hasLength(dateAsString)){
                return LocalDate.parse(dateAsString, FORMATTER);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Invalid date format for field '%s'. Value '%s' does not match expected format: %s",
                    fieldName, dateAsString, "yyyyMMdd (e.g. 20501201)"));
        }
        return null;
    }
}