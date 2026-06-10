package com.example.api.service;

import com.example.api.dto.SendPasswordResetEmailRequestDto;
import org.springframework.http.ResponseEntity;

public interface SendPasswordResetEmailService {
    void sendPasswordResetEmail(SendPasswordResetEmailRequestDto sendPasswordResetEmailRequestDto);
}
