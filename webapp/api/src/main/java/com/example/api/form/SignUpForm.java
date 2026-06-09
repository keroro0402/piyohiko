package com.example.api.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {
    @NotBlank(message = "メールアドレスを入力してください")
    @Size(min = 1, max = 256, message = "メールアドレスは1~256文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "メールアドレスの形式が不正です")
    private String email;
    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 8, max = 64, message = "パスワードは8~64文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "パスワードは半角英数字のみで入力してください")
    private String password;
    private String securityPhrase;
}
