package com.ppcb.cafemerchant.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class Serializers extends JsonSerializer<Object> {
    public static final JsonSerializer<Object> EMPTY_STRING_SERIALIZER_INSTANCE = new EmptyStringSerializer();
    public static final JsonSerializer<Object> NULL_TO_ZERO_SERIALIZER_INSTANCE = new NullToZeroSerializer();


    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString("");
    }

    private static class EmptyStringSerializer extends JsonSerializer<Object> {
        public EmptyStringSerializer() {}

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {
                jsonGenerator.writeString("");
        }

    }

    private static class NullToZeroSerializer extends JsonSerializer<Object> {
        public NullToZeroSerializer() {}

        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
                throws IOException, JsonProcessingException {
            jsonGenerator.writeNumber(0);
        }

    }

}
