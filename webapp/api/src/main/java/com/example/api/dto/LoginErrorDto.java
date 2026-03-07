package com.example.api.dto;

import lombok.Data;

@Data
public class LoginErrorDto {
    private String errorCode;
    private String message;
}
