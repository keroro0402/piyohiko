package com.example.api.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;

@Data
public class ResetPasswordForm {
    @NotBlank(message = "シークレットコードを入力してください")
    @Pattern(regexp = "^[0-9]{3}$", message = "シークレットコードは3桁半角数字で入力してください")
    private String secretCode;
    @NotBlank(message = "パスワードを入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "パスワードは半角英数字のみで入力してください")
    private String password;
}
