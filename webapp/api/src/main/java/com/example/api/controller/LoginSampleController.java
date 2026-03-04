package com.example.api.controller;

import com.example.api.entity.UserSample;
import com.example.api.service.LoginSampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginSampleController {

    private final LoginSampleService loginSampleService;

    @PostMapping("/login")
    public UserSample login(
            @RequestParam String loginId,
            @RequestParam String password
    ){
        return loginSampleService.login(loginId, password);
    }

}
