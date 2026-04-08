package com.example.api.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer userId;
    private String loginId;
    private String password;
    private String role;
}
