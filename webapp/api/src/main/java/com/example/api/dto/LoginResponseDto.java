package com.example.api.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private Integer userId;
    private String loginId;
    private String token;
}
