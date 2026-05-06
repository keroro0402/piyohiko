package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "ログインIDは必須です")
    private String loginId;
    @NotBlank(message = "パスワードは必須です")
    private String password;
    private long expiration;
}
