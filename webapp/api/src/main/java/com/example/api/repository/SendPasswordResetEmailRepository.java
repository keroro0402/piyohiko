package com.example.api.repository;

import com.example.api.entity.SendPasswordResetEmail;

public interface SendPasswordResetEmailRepository{
    void createPasswordResetRequest(SendPasswordResetEmail sendPasswordResetEmail);
}
