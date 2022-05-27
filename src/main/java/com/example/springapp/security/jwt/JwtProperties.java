package com.example.springapp.security.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String secretKey = "secret";
    private Long validityInMs = 100000000L;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getValidityInMs() {
        return validityInMs;
    }

    public void setValidityInMs(Long validityInMs) {
        this.validityInMs = validityInMs;
    }
}
