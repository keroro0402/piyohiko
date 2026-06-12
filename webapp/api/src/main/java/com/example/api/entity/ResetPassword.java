package com.example.api.entity;


import java.time.LocalDateTime;

public record ResetPassword(
        Long id,
        Integer userId,
        String secretCode,
        LocalDateTime expiryDate,
        Boolean isUsed
){}
