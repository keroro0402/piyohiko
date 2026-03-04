package com.example.api.controller;

import com.example.api.entity.User;
import com.example.api.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    User login(
            @RequestParam String loginId,
            @RequestParam String password
    ){
        return loginService.login(loginId, password);
    }
}
