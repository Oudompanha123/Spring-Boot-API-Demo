package com.ppcb.cafemerchant.api.security;

import com.ppcb.cafemerchant.api.payload.auth.AuthSuccessEvent;
import com.ppcb.cafemerchant.common.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginSuccessEventListener {
    private final UserRepository userRepository;

    @EventListener
    public void handleSuccess(AuthSuccessEvent event) {
        var securityUser = event.securityUser();
        userRepository.updateLastLogin(securityUser.user().getUserId());
    }
}
