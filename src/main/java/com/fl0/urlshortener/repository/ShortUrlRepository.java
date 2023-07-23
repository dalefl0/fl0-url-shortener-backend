package com.fl0.urlshortener.repository;

import com.fl0.urlshortener.entity.ShortUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortUrlRepository extends JpaRepository<ShortUrlEntity, Long> {
    ShortUrlEntity findByKey(String key);
    ShortUrlEntity findByFullUrl(String fullUrl);
}
