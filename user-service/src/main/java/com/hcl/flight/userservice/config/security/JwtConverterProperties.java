package com.hcl.flight.userservice.config.security;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Configuration
@ConfigurationProperties(prefix = "conf-jwt")
public class JwtConverterProperties {
    private String resourceId;
    private String principalAttribute;
    public JwtConverterProperties(String resourceId, String principalAttribute) {
        this.resourceId = resourceId;
        this.principalAttribute = principalAttribute;
    }

    public JwtConverterProperties() {
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getPrincipalAttribute() {
        return principalAttribute;
    }

    public void setPrincipalAttribute(String principalAttribute) {
        this.principalAttribute = principalAttribute;
    }
}
