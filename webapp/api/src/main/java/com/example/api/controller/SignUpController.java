package com.example.api.controller;

import com.example.api.dto.SignUpRequestDto;
import com.example.api.dto.SignUpResponseDto;
import com.example.api.form.SignUpForm;
import com.example.api.service.RegisterService;
import com.example.api.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {
    private final RegisterService service;

    private final SignUpService signUpService;
    @PostMapping("/signup")
    public SignUpResponseDto registerNewUser(@RequestBody @Valid SignUpForm signUpForm) {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setLoginId(signUpForm.getLoginId());
        signUpRequestDto.setPassword(signUpForm.getPassword());
        signUpRequestDto.setSecurityPhrase(signUpForm.getSecurityPhrase());
        return signUpService.signUp(signUpRequestDto);
    }

}
