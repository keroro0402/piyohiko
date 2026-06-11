package com.example.api.controller;

import com.example.api.dto.ResetPasswordRequestDto;
import com.example.api.dto.ResetPasswordResponseDto;
import com.example.api.form.ResetPasswordForm;
import com.example.api.service.ResetPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ResetPasswordController {

    private final ResetPasswordService resetPasswordService;

    @PostMapping("/password-reset")
    public ResetPasswordResponseDto resetPassword(
            @RequestBody @Validated ResetPasswordForm resetPasswordForm
    ){
        ResetPasswordRequestDto resetPasswordRequestDto = new ResetPasswordRequestDto();
        resetPasswordRequestDto.setEmail(resetPasswordForm.getEmail());
        resetPasswordRequestDto.setSecretCode(resetPasswordForm.getSecretCode());
        resetPasswordRequestDto.setPassword(resetPasswordForm.getPassword());
        return resetPasswordService.resetPassword(resetPasswordRequestDto);
    }
}
