package com.fl0.urlshortener.service;

import com.fl0.urlshortener.config.ShortUrlConfig;
import com.fl0.urlshortener.dto.ShortUrlRequest;
import com.fl0.urlshortener.dto.ShortUrlResponse;
import com.fl0.urlshortener.entity.ShortUrlEntity;
import com.fl0.urlshortener.repository.ShortUrlRepository;
import com.fl0.urlshortener.util.ShortUrlUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

    private final ShortUrlRepository repository;
    private final ShortUrlUtil util;
    private final ShortUrlConfig config;

    public ShortUrlResponse createShortUrl(ShortUrlRequest request) {
        String fullUrl = request.getUrl();

        ShortUrlEntity existingShortUrl = repository.findByFullUrl(fullUrl);

        if (existingShortUrl != null) {
            String shortUrl = util.generateShortUrl(existingShortUrl.getKey());
            return ShortUrlResponse.builder().url(shortUrl).build();
        } else {
            String newKey = util.generateUniqueKey();
            ShortUrlEntity newEntity = ShortUrlEntity.builder()
                    .key(newKey).fullUrl(fullUrl).clickCount(0L)
                    .build();
            repository.save(newEntity);
            String responseUrl = util.generateShortUrl(newKey);
            return ShortUrlResponse.builder().url(responseUrl).build();
        }
    }

    public RedirectView getFullUrl(String key) {
        ShortUrlEntity entityInDb = repository.findByKey(key);

        if (entityInDb == null) {
            return new RedirectView(config.getBaseUrl());
        } else {
            entityInDb.setClickCount(entityInDb.getClickCount() + 1);
            repository.save(entityInDb);
            return new RedirectView(entityInDb.getFullUrl());
        }
    }
}