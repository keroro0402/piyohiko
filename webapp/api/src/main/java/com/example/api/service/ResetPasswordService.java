package com.example.api.service;

import com.example.api.dto.ResetPasswordRequestDto;
import com.example.api.dto.ResetPasswordResponseDto;

public interface ResetPasswordService {
    ResetPasswordResponseDto resetPassword(ResetPasswordRequestDto resetPasswordRequestDto);
}
