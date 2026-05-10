package com.example.api.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String accessToken;
    private long expiration;
}
