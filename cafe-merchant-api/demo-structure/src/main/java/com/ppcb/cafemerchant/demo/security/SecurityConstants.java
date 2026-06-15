package com.ppcb.cafemerchant.demo.security;

public final class SecurityConstants {

    private SecurityConstants() {
    }

    public static final String[] PERMIT_ALL = {
            "/api/v1/demo/**",
            "/actuator/**",
            "/favicon.ico",
    };
}
