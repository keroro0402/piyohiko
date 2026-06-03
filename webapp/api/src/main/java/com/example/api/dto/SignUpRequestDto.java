package com.example.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    private String loginId;
    private String password;
    private String securityPhrase;
}
