package com.ppcb.cafemerchant.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ppcb.cafemerchant.common.components.AbstractEnumConverter;
import com.ppcb.cafemerchant.common.components.GenericEnum;

/**
 * A class can be used for getting UserStatus enum
 */
public enum UserType implements GenericEnum<UserType, String> {
    STAFF("staff")
    ;

    private final String value;

    private UserType(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static UserType fromValue(String value) {
        for(UserType my: UserType.values()) {
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

        if("staff".equals(value)) label = "staff";



        return label;
    }

    public static class Converter extends AbstractEnumConverter<UserType, String> {

        public Converter() {
            super(UserType.class);
        }

    }

}
