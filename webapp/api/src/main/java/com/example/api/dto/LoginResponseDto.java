package com.example.api.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private UserDto user;
    private String accessToken;
    private long expiration;
}
