package com.ppcb.cafemerchant.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "rsa")
public record RsaKeysProperties(
        RSAPublicKey publicKey,
        RSAPrivateKey privateKey
){
}
