package com.example.api.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
    private long expiration;
}
