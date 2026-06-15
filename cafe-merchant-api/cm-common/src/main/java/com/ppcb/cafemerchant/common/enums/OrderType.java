package com.ppcb.cafemerchant.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ppcb.cafemerchant.common.components.AbstractEnumConverter;
import com.ppcb.cafemerchant.common.components.GenericEnum;

/**
 * A class can be used for getting UserStatus enum
 */
public enum OrderType implements GenericEnum<OrderType, String> {
    PICK("pickup"),
    ;

    private final String value;

    private OrderType(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static OrderType fromValue(String value) {
        for(OrderType my: OrderType.values()) {
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

        if("pick".equals(value)) label = "pick";


        return label;
    }

    public static class Converter extends AbstractEnumConverter<OrderType, String> {

        public Converter() {
            super(OrderType.class);
        }

    }

}
