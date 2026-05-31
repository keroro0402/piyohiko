package com.example.api.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {
    @NotBlank(message = "ログインIDを入力してください")
    @Size(min = 4, max = 20, message = "ログインIDは4~20文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "ログインIDは半角英数字のみで入力してください")
    private String loginId;
    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 8, max = 64, message = "パスワードは8~64文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "パスワードは半角英数字のみで入力してください")
    private String password;
}
