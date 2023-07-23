package com.fl0.urlshortener.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShortUrlResponse {
    private String url;
    private String error;
}