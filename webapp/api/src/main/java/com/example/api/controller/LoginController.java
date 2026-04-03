package com.example.api.controller;

import com.example.api.dto.LoginRequestDto;
import com.example.api.dto.LoginResponseDto;
import com.example.api.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(
            @RequestBody @Valid LoginRequestDto loginRequestDto
            ){
        return loginService.login(loginRequestDto.getLoginId(), loginRequestDto.getPassword());
    }
}
