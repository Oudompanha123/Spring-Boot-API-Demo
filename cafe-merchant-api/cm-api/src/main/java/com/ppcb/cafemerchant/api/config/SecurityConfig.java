package com.ppcb.cafemerchant.api.config;

import com.ppcb.cafemerchant.api.service.auth.JpaUserDetailsService;
import com.ppcb.cafemerchant.common.security.handler.AuthAccessDeniedHandler;
import com.ppcb.cafemerchant.common.security.handler.AuthUnauthorizedHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager userAuthProvider(
            PasswordEncoder passwordEncoder,
            JpaUserDetailsService jpaUserDetailsService
    ) {
        var userProvider = new DaoAuthenticationProvider(jpaUserDetailsService);
        userProvider.setPasswordEncoder(passwordEncoder);
        userProvider.setHideUserNotFoundExceptions(false);
        return new ProviderManager(userProvider);
    }

    private static final String[] PERMIT_ALL = {
            "/api/v1/ca/auth/login",
            "/api/v1/ca/auth/link",
            "/image/**",
            "/favicon.ico",
            "/api/v1/image/**",
    };

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthUnauthorizedHandler unauthorizedHandler,
            AuthAccessDeniedHandler accessDeniedHandler
    ) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(configurer ->
                        configurer.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                PERMIT_ALL
                        ).permitAll()

                        .requestMatchers("/api/ca/v1/**").hasAnyAuthority("SYS_ADMIN")
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(ex ->
                        ex.accessDeniedHandler(accessDeniedHandler)
                                .authenticationEntryPoint(unauthorizedHandler)
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .authenticationEntryPoint(unauthorizedHandler)
                        .accessDeniedHandler(accessDeniedHandler)
                        .jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter()))
                )
                .build();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }
}
