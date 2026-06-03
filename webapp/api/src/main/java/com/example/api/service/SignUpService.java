package com.example.api.service;

import com.example.api.dto.SignUpRequestDto;
import com.example.api.dto.SignUpResponseDto;

public interface SignUpService {
    SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto);
}
