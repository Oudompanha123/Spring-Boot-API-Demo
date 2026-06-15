package com.ppcb.cafemerchant.common.config.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = {
        RsaKeysProperties.class
        , JwtProperties.class
        , FileInfoProperties.class
})
public class PropertiesConfig {
}
