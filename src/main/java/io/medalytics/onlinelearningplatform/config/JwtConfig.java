package io.medalytics.onlinelearningplatform.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(value = "application.jwt")
public class JwtConfig {

    private String expiration;
    private String prefix;
    private String secretKey;

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getExpiration() {
        return expiration;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
