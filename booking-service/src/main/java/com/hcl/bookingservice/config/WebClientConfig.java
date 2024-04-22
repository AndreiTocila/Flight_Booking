package com.hcl.bookingservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig
{
    @Value("${user-service.url}")
    private String userServiceUrl;
    @Bean
    public WebClient webClient(WebClient.Builder builder)
    {
        return builder.baseUrl(userServiceUrl).build();
    }
}
