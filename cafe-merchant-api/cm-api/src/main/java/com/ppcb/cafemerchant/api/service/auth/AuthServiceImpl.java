package com.ppcb.cafemerchant.api.service.auth;

import com.ppcb.cafemerchant.api.payload.auth.AuthSuccessEvent;
import com.ppcb.cafemerchant.api.payload.auth.LinkLoginRequest;
import com.ppcb.cafemerchant.api.payload.auth.LoginRequest;
import com.ppcb.cafemerchant.api.payload.auth.LoginResponse;
import com.ppcb.cafemerchant.api.security.LinkAuthenticationProvider;
import com.ppcb.cafemerchant.api.security.LinkAuthenticationToken;
import com.ppcb.cafemerchant.common.components.constant.StatusCode;
import com.ppcb.cafemerchant.common.components.exception.BusinessException;
import com.ppcb.cafemerchant.common.components.logging.AppLogManager;
import com.ppcb.cafemerchant.common.security.JwtTokenUtil;
import com.ppcb.cafemerchant.common.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager userAuthProvider;
    private final LinkAuthenticationProvider linkAuthProvider;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public LoginResponse login(LoginRequest payload) throws Throwable {

        try {
            Authentication authenticate = userAuthProvider.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            payload.username(),
                            payload.password()
                    )
            );
            SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();

            eventPublisher.publishEvent(new AuthSuccessEvent(securityUser));

            String token = jwtTokenUtil.doGenerateToken(securityUser);
            String refreshToken = jwtTokenUtil.doGenerateRefreshToken(securityUser);

            return new LoginResponse(token, refreshToken, "Bearer", jwtTokenUtil.getExpireIn());

        }   catch (UsernameNotFoundException ex){
            throw new BusinessException(StatusCode.USER_NOT_FOUND, ex.getMessage());
        } catch (BadCredentialsException e) {
            AppLogManager.error(e);
            throw new BusinessException(StatusCode.BAD_CREDENTIALS, e);
        } catch (DisabledException e){
            throw new BusinessException(StatusCode.USER_DISABLED);
        }catch (LockedException e) {
            throw new BusinessException(StatusCode.USER_LOCKED);
        }catch (Exception e){
            AppLogManager.error(e);
            throw new BusinessException(StatusCode.BAD_REQUEST, e.getLocalizedMessage());
        }
    }

    @Override
    public LoginResponse linkLogin(LinkLoginRequest payload) throws Throwable{

        Authentication authenticate = linkAuthProvider.authenticate(
                new LinkAuthenticationToken(payload.loginLinkUrl())
        );
        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();

        eventPublisher.publishEvent(new AuthSuccessEvent(securityUser));

        String token = jwtTokenUtil.doGenerateToken(securityUser);
        String refreshToken = jwtTokenUtil.doGenerateRefreshToken(securityUser);

        return new LoginResponse(token, refreshToken, "Bearer", jwtTokenUtil.getExpireIn());
    }

}
