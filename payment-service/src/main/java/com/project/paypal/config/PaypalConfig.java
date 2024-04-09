package com.project.paypal.config;

import com.paypal.base.rest.APIContext;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "{com.project.paypal.repository.OrderRepository}")
public class PaypalConfig {

    @Value("${paypal.client-id}")
    private String clientId;
    @Value("${paypal.client-secret}")
    private String clientSecret;
    @Value("${paypal.mode}")
    private String mode;

    @Bean
    public APIContext apiContext(){
        return new APIContext(clientId, clientSecret, mode);
    }

    @Bean
    public PayPalEnvironment payPalEnvironment(){
        return new PayPalEnvironment.Sandbox(clientId, clientSecret);
    }

    @Bean
    public PayPalHttpClient payPalHttpClient(PayPalEnvironment environment){
        return new PayPalHttpClient(environment);
    }
}
