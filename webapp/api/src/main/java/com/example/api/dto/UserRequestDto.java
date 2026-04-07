package com.example.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequestDto {
    @NotBlank(message = "loginIdは必須です")
    private String loginId;
    @NotBlank(message = "passwordは必須です")
    private String password;
}
