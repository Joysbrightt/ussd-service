package com.Joysbrightt.ussdservice.config;

import lombok.AccessLevel;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "decoded")
@Data
public class UssdApplicationConfiguration {

    private CacheConfigurationProperties cache;
    @Getter(value= AccessLevel.PUBLIC)
    private class CacheConfigurationProperties{
        private Integer port;
        private String host;
        private  String password;
        private String defaultTtl;
    }
}
