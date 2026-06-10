package com.example.api.controller;

import com.example.api.dto.SendPasswordResetEmailRequestDto;
import com.example.api.form.SendPasswordResetEmailForm;
import com.example.api.service.SendPasswordResetEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SendPasswordResetEmailController {

    private final SendPasswordResetEmailService sendPasswordResetEmailService;

    @PostMapping("/password-reset-email")
    public void sendPasswordResetEmail(
            @RequestBody
            @Validated
            SendPasswordResetEmailForm sendPasswordResetEmailForm
    ){
        SendPasswordResetEmailRequestDto sendPasswordResetEmailRequestDto = new SendPasswordResetEmailRequestDto();
        sendPasswordResetEmailRequestDto.setEmail(sendPasswordResetEmailForm.getEmail());
        sendPasswordResetEmailService.sendPasswordResetEmail(sendPasswordResetEmailRequestDto);
    };

}
