package com.example.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiErrorDto {
    private String errorCode;
    private List<String> message;
    private String timestamp;
    private String path;
}
