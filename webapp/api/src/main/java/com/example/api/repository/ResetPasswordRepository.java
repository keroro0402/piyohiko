package com.example.api.repository;

import com.example.api.entity.ResetPassword;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ResetPasswordRepository {
    Optional<ResetPassword> findByUserIdAndSecretCodeAndIsUsedFalseAndExpiryDateAfter(
            Integer userId,
            String secretCode,
            LocalDateTime now
    );
}
