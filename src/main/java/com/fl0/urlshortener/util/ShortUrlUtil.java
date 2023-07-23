package com.fl0.urlshortener.util;

import com.fl0.urlshortener.config.ShortUrlConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class ShortUrlUtil {

    private final ShortUrlConfig config;

    @Autowired
    public ShortUrlUtil(ShortUrlConfig config) {
        this.config = config;
    }

    public String generateUniqueKey() {
        int keyLength = config.getKeyLength();
        String allowedCharacters = config.getAllowedCharacters();

        StringBuilder keyBuilder = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < keyLength; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            keyBuilder.append(allowedCharacters.charAt(randomIndex));
        }

        return keyBuilder.toString();
    }

    public String generateShortUrl(String key) {
        String baseUrl = config.getBaseUrl();
        return baseUrl + "/" + key;
    }

}
