package com.example.api.service;

import com.example.api.dto.LoginResponseDto;

public interface LoginService {
    LoginResponseDto login(String loginId, String password);
}
