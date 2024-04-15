package com.hcl.bookingservice.integration.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@TestConfiguration
public class KafkaIntegrationTestConfig
{
    @Bean
    @Primary
    public MongoTemplate mongoTemplate()
    {
        // Return a mock or null MongoTemplate bean
        return null;
    }
}
