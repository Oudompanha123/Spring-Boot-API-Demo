package com.ppcb.cafemerchant.common.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class AuthHelper {
    private static final String ISS_CLAIM = "iss";
    private static final String ERP_SYS_ID_CLAIM = "erp_sys_id";
    private static final String SCOPE_CLAIM = "scope";

    public static Authentication getAuth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Jwt getJwt(){
        if(getAuth().getPrincipal() instanceof Jwt){
            return (Jwt) getAuth().getPrincipal();
        }
        return null;
    }

    public static <T> T getClaim(Jwt jwt, String claim) {

        if(jwt == null){
            return null;
        }

        T value = null;

        if (jwt.hasClaim(claim)) {
            value = jwt.getClaim(claim);
        }

        return value ;
    }

    public static String getIssClaim(){
        return getClaim(getJwt(), ISS_CLAIM);
    }

    public static String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        return authentication.getName();
    }


    public static String getUserId() {
        return getCurrentUser();
    }

    public static Long getPKId() {
        return 1L;
    }
    public static Long getId() {
        return getPKId();
    }

    public static Long getErpSystemId() {
        return getClaim(getJwt(), ERP_SYS_ID_CLAIM);
    }

    public static String getScope() {
        return getClaim(getJwt(), SCOPE_CLAIM);
    }
}


