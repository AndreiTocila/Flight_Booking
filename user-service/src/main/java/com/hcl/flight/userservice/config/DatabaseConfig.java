package com.hcl.flight.userservice.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.hcl.flight.userservice.repository"})
@EntityScan(basePackages = {"com.hcl.flight.userservice.model"})
public class DatabaseConfig {
}
