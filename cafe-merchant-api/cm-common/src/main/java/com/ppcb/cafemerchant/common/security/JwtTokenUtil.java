package com.ppcb.cafemerchant.common.security;

import com.ppcb.cafemerchant.common.config.properties.JwtProperties;
import com.ppcb.cafemerchant.common.enums.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final JwtEncoder jwtEncoder;
    private final JwtProperties jwtConfig;

    public long getExpireIn(){
        return jwtConfig.expiration().getSeconds();
    }

    public String doGenerateToken(SecurityUser securityUser) {

        Instant now = Instant.now();

        Map<String, Object> claim = new HashMap<>();

//        var scope = securityUser.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));
//
//        claim.put("scope", scope);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(securityUser.getUsername())
                .issuer(AuthProvider.CLIENT.getValue())
                .issuedAt(now)
                .expiresAt(now.plus(jwtConfig.expiration().getSeconds(), ChronoUnit.SECONDS))
                .claims(c -> c.putAll(claim))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String doGenerateRefreshToken(SecurityUser securityUser) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(securityUser.getUsername())
                .issuer(AuthProvider.CLIENT.getValue())
                .issuedAt(now)
                .expiresAt(now.plus(jwtConfig.refreshExpiration().getSeconds(), ChronoUnit.SECONDS))
                .claim("tokenType", "refresh")
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
