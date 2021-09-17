package com.springproj.schedulebuilder.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Data
@Primary
@Component
@ConfigurationProperties("custom.jwt")
public class CustomJwtConfigProperties {
    private Integer hoursValid;
    private String secret;
}
