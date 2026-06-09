package com.example.api.controller;

import com.example.api.dto.LoginRequestDto;
import com.example.api.dto.LoginResponseDto;
import com.example.api.form.LoginForm;
import com.example.api.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
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
            @RequestBody @Valid LoginForm loginForm
            ){
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail(loginForm.getEmail());
        loginRequestDto.setPassword( loginForm.getPassword());
        loginRequestDto.setExpiration(loginForm.getExpiration());
        return loginService.login(loginRequestDto);
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin認証";
    }

    @GetMapping("/users")
    public String users(){return "user認証OK";}
}
