package com.example.api.repository;

import com.example.api.entity.ResetPassword;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ResetPasswordRepository {
    Optional<ResetPassword> findByEmailAndSecretCodeAndIsUsedFalseAndExpiryDateAfter(
            String email,
            String secretCode,
            LocalDateTime now
    );
}
