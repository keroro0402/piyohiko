package com.example.api.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String loginId;
    private String password;
}
