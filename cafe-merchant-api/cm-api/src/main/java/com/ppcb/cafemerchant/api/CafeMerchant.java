package com.ppcb.cafemerchant.api;

import com.ppcb.cafemerchant.common.config.properties.FileInfoProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan("com.ppcb.cafemerchant.common.domain")
@EnableJpaRepositories("com.ppcb.cafemerchant.common.domain")
@SpringBootApplication(scanBasePackages = "com.ppcb.cafemerchant")
@EnableConfigurationProperties(FileInfoProperties.class)
public class CafeMerchant {

    public static void main(String[] args) {
        SpringApplication.run(CafeMerchant.class, args);
    }

}
