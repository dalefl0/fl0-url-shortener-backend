package com.fl0.urlshortener.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "short-url")
@Getter
@Setter
public class ShortUrlConfig {
    private String baseUrl;
    private String allowedCharacters;
    private int keyLength;
}
