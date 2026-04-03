package com.example.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class LoginErrorDto {
    private String errorCode;
    private List<String> message;
}
