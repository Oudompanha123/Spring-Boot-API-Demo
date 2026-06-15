package com.ppcb.cafemerchant.api.security;

import com.ppcb.cafemerchant.common.components.constant.StatusCode;
import com.ppcb.cafemerchant.common.components.exception.BusinessException;
import com.ppcb.cafemerchant.common.domain.user.UserRepository;
import com.ppcb.cafemerchant.common.enums.LoginType;
import com.ppcb.cafemerchant.common.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LinkAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final CustomUserDetailsChecker userDetailsChecker = new CustomUserDetailsChecker();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        var token = (String) authentication.getCredentials();

        var user = userRepository.findByLoginLinkUrl(token)
                .orElseThrow(() -> new UsernameNotFoundException(token));

        SecurityUser securityUser = new SecurityUser(user);
        userDetailsChecker.check(securityUser);

        return new LinkAuthenticationToken(securityUser, token, securityUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LinkAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private static class CustomUserDetailsChecker implements UserDetailsChecker {

        @Override
        public void check(UserDetails toCheck) {

            var securityUser = (SecurityUser) toCheck;

            if (!securityUser.isAccountNonLocked()) {
                throw new BusinessException(StatusCode.USER_LOCKED);
            }
            if (!securityUser.isEnabled()) {

                throw new BusinessException(StatusCode.USER_DISABLED);
            }
            if (!securityUser.isAccountNonExpired()) {
                throw new BusinessException(StatusCode.USER_EXPIRED);
            }

            if (!securityUser.isCredentialsNonExpired()) {
                throw new BusinessException(StatusCode.CREDENTIALS_EXPIRED);
            }

            if(!LoginType.LINK.equals(securityUser.user().getLoginType())){
                throw new BusinessException(StatusCode.INVALID_LOGIN_TYPE);
            }

        }
    }

}
