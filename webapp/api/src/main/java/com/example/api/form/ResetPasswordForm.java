package com.example.api.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class ResetPasswordForm {
    @NotBlank(message = "メールアドレスを入力してください")
    @Size(min = 1, max = 256, message = "メールアドレスは1~256文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "メールアドレスの形式が不正です")
    private String email;
    @NotBlank(message = "シークレットコードを入力してください")
    @Pattern(regexp = "^[0-9]{3}$", message = "シークレットコードは3桁半角数字で入力してください")
    private String secretCode;
    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 8, max = 64, message = "パスワードは8~64文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "パスワードは半角英数字のみで入力してください")
    private String password;
}
