package com.ppcb.cafemerchant.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ppcb.cafemerchant.common.components.AbstractEnumConverter;
import com.ppcb.cafemerchant.common.components.GenericEnum;

/**
 * A class can be used for getting UserStatus enum
 */
public enum PaymentStatus implements GenericEnum<PaymentStatus, String> {
    PENDING("pending"),
    COMPLETED("completed"),
    ;

    private final String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static PaymentStatus fromValue(String value) {
        for(PaymentStatus my: PaymentStatus.values()) {
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

        if("pending".equals(value)) label = "pending";
        else if("completed".equals(value)) label = "completed";

        return label;
    }

    public static class Converter extends AbstractEnumConverter<PaymentStatus, String> {

        public Converter() {
            super(PaymentStatus.class);
        }

    }

}
