package com.example.api.dto;

import lombok.Data;

@Data
public class UserDto {
    private Integer userId;
    private String email;
    private String role;
}
