package com.ppcb.cafemerchant.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountSerializer extends StdSerializer<BigDecimal> {

    public AmountSerializer() {
        super(BigDecimal.class);
    }

    public void serialize(BigDecimal value, JsonGenerator generator, SerializerProvider provider) throws IOException {
        if (value != null)
            generator.writeNumber(value.setScale(2,RoundingMode.HALF_EVEN));
        else{
            generator.writeNumber(0);
        }
    }

}