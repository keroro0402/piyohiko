package com.example.api.entity;


import java.time.LocalDateTime;

public record ResetPassword(
        Long id,
        String email,
        String secretCode,
        LocalDateTime expiryDate,
        Boolean isUsed
){}
