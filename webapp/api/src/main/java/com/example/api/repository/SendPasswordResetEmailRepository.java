package com.example.api.repository;

import com.example.api.entity.PasswordResetRequest;

public interface SendPasswordResetEmailRepository{
    void createPasswordResetRequest(PasswordResetRequest passwordResetRequest);
}
