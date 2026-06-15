package com.ppcb.cafemerchant.common.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.ppcb.cafemerchant.common.components.logging.AppLogManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountDeserializer extends StdDeserializer<BigDecimal> {

    public AmountDeserializer() {
        super(String.class);
    }

    public BigDecimal deserialize(JsonParser parser, DeserializationContext context) {
        try {
            String payload = parser.readValueAs(String.class);

            if (StringUtils.isNotBlank(payload)) {
                return NumberUtils.createBigDecimal(payload).setScale(2, RoundingMode.HALF_EVEN);
            }
        } catch(Exception e) {
            AppLogManager.error(e);
            return BigDecimal.ZERO;
        }

        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal getNullValue(DeserializationContext ctxt) throws JsonMappingException {
        return BigDecimal.ZERO;
    }
}