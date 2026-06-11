package com.example.api.service;

import com.example.api.dto.SendPasswordResetEmailRequestDto;
import com.example.api.dto.SendPasswordResetEmailResponseDto;
import org.springframework.http.ResponseEntity;

public interface SendPasswordResetEmailService {
    SendPasswordResetEmailResponseDto sendPasswordResetEmail(SendPasswordResetEmailRequestDto sendPasswordResetEmailRequestDto);
}
