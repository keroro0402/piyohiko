package com.example.api.controller;

import com.example.api.dto.ResetPasswordResponseDto;
import com.example.api.form.ResetPasswordForm;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResetPasswordController {
    @PostMapping("/password-reset")
    public ResetPasswordResponseDto resetPassword(
            @RequestBody @Validated ResetPasswordForm resetPasswordForm
    ){
        ResetPasswordResponseDto resetPasswordResponseDto = new ResetPasswordResponseDto();
        resetPasswordResponseDto.setMessage("ここまで来た");
        return resetPasswordResponseDto;
    }
}
