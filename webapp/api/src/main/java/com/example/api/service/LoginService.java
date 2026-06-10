package com.example.api.service;

import com.example.api.dto.LoginRequestDto;
import com.example.api.dto.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
