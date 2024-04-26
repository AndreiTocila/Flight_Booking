package com.hcl.flight.userservice.config.security;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@KeycloakConfiguration
public class SecurityConfig {
  private final JwtConverter jwtConverter;

  public SecurityConfig(JwtConverter jwtConverter) {
    this.jwtConverter = jwtConverter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    http.oauth2ResourceServer(
        oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)));

    return http.build();
  }

  @Bean
  public KeycloakConfigResolver keycloakConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }

  @Bean
  protected SessionRegistry buildSessionRegistry() {
    return new SessionRegistryImpl();
  }

  @Bean
  public Keycloak getKeycloakInstance() {
    return KeycloakBuilder.builder()
        .serverUrl("http://localhost:8080/")
        .realm("spring-app")
        .grantType(OAuth2Constants.PASSWORD)
        .username("admin")
        .password("admin")
        .clientId("login-app")
        .clientSecret("ZlnxzwMYaSOv5ygztmVPtcqBlBxyLBv2")
        .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
        .build();
  }

  @Bean
  public Keycloak keycloak(@Value("${keycloak.auth-server-url}") String serverUrl) {
    return Keycloak.getInstance(serverUrl, "master", "admin", "admin", "admin-cli");
  }
}