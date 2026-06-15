package com.ppcb.cafemerchant.common.security;

import com.ppcb.cafemerchant.common.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public record SecurityUser(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

//        authorities.add(new SimpleGrantedAuthority(user.get().getRoleCode()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user.getIsActive();
    }

    @Override
    public boolean isAccountNonLocked() {

        Instant accountLockedUntil = user.getAccountLockedUntil();

        // Account is locked if lockUntil is set and current time is before that timestamp
        if (Objects.nonNull(accountLockedUntil) && Instant.now().isBefore(accountLockedUntil)) {
            return false; // Account is locked
        }

        return true; // Account is not locked
    }
}
