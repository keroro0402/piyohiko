package com.example.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDto {
    private String errorCode;
    private List<String> message;
    private String timestamp;
    private String path;
}
