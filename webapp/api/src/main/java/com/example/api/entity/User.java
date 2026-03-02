package com.example.api.entity;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String loginId;
    private String password;
}
