package com.ppcb.cafemerchant.common.config;

import com.ppcb.cafemerchant.common.config.properties.FileInfoProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
class MvcConfig implements WebMvcConfigurer {
    private final FileInfoProperties fileInfoProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/v1/image/**")
                .addResourceLocations("file:" + fileInfoProperties.getServerPath() + "/");
    }
}
