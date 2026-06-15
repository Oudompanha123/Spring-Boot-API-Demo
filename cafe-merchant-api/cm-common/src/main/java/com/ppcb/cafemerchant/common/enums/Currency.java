package com.ppcb.cafemerchant.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ppcb.cafemerchant.common.components.AbstractEnumConverter;
import com.ppcb.cafemerchant.common.components.GenericEnum;

/**
 * A class can be used for getting UserStatus enum
 */
public enum Currency implements GenericEnum<Currency, String> {
    USD("USD"),
    KHR("KHR")
    ;

    private final String value;

    private Currency(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static Currency fromValue(String value) {
        for(Currency my: Currency.values()) {
            if(my.value.equals(value)) {
                return my;
            }
        }

        return null;
    }

    /**
     * Method getValue : Get Enum value
     * @return Enum value
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Method getLabel : Get Enum Label
     * @return Enum Label
     */
    @Override
    public String getLabel() {
        String label = "(no label)";

        if("USD".equals(value)) label = "USD";
        else if("KHR".equals(value)) label = "KHR";


        return label;
    }

    public static class Converter extends AbstractEnumConverter<Currency, String> {

        public Converter() {
            super(Currency.class);
        }

    }

}
