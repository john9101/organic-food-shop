package com.spring.project.organicfoodshop.config;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfiguration {

    @Value("${payment.paypal.client-id}")
    private String clientId;

    @Value("${payment.paypal.secret-key}")
    private String secretKey;

    @Value("${payment.paypal.mode}")
    private String mode;

    @Bean
    public APIContext apiContext() {
        return new APIContext(clientId, secretKey, mode);
    }
}
