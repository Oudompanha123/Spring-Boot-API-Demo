package com.ppcb.cafemerchant.api.security;

import com.ppcb.cafemerchant.common.security.SecurityUser;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LinkAuthenticationToken extends AbstractAuthenticationToken {

    private final String token;
    private final SecurityUser securityUser;

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the principal
     *                    represented by this authentication object.
     */
    public LinkAuthenticationToken(String token) {
        super(null);
        this.token = token;
        this.securityUser = null;
        setAuthenticated(false);
    }

    public LinkAuthenticationToken(SecurityUser principal, String token,
                                   Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
        this.securityUser = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return securityUser;
    }
}
