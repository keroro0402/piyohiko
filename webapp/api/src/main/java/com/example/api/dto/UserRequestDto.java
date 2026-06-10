package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "メールアドレスは必須です")
    private String email;
    @NotBlank(message = "パスワードは必須です")
    private String password;
}
