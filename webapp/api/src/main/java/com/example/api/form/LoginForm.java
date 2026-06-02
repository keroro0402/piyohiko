package com.example.api.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotBlank(message = "ログインIDは必須です")
    private String loginId;
    @NotBlank(message = "パスワードは必須です")
    private String password;
    private long expiration;
}
