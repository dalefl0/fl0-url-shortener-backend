package com.fl0.urlshortener.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "short_urls")
public class ShortUrlEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String key;

    @Column(nullable = false)
    private String fullUrl;

    @Column(nullable = false)
    private Long clickCount;
}