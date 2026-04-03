package com.example.api.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String loginId;
    private String password;
}
