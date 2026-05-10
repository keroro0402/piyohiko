package com.example.api.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private UserDto user;
    private TokenDto token;
}
