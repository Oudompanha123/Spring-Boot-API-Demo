package com.ppcb.cafemerchant.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ppcb.cafemerchant.common.components.AbstractEnumConverter;
import com.ppcb.cafemerchant.common.components.GenericEnum;

/**
 * A class can be used for getting UserStatus enum
 */
public enum LoginType implements GenericEnum<LoginType, String> {
    CREDENTIAL("credential"),
    LINK("link"),
    ;

    private final String value;

    private LoginType(String value) {
        this.value = value;
    }

    /**
     * Method fromValue : Check Enum value
     *
     * @param value value that have to check
     * @return enum value
     */
    @JsonCreator
    public static LoginType fromValue(String value) {
        for(LoginType my: LoginType.values()) {
            if(my.value.equals(value)) {
                return my;
            }
        }

        return LoginType.LINK;
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

        if("credential".equals(value)) label = "credential";
        if("link".equals(value)) label = "link";


        return label;
    }

    public static class Converter extends AbstractEnumConverter<LoginType, String> {

        public Converter() {
            super(LoginType.class);
        }

    }

}
