package com.ppcb.cafemerchant.common.security;//package com.kosign.weinvoice.common.security;
//
//import com.kosign.weinvoice.common.domain.repository.UserEntityRepository;
//import com.kosign.weinvoice.common.enums.AuthProvider;
//import lombok.RequiredArgsConstructor;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtClaimNames;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
//
//    private final UserEntityRepository userInfoRepository;
//
//    @Override
//    public AbstractAuthenticationToken convert(Jwt jwt) {
//        var user = userInfoRepository.find(jwt.getSubject())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//        // Get the issuer of the token to determine the authentication provider (USER, PARTNER)
//        var issuer = jwt.getClaimAsString(JwtClaimNames.ISS);
//
//        return new MyUserJwtAuthenticationToken<>(jwt, new SecurityUser(user, AuthProvider.fromValue(issuer)));
//    }
//
//}
