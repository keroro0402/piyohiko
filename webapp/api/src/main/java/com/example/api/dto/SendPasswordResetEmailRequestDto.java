package com.example.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendPasswordResetEmailRequestDto {
    private String email;
}
