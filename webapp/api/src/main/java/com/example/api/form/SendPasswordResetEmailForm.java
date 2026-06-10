package com.example.api.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendPasswordResetEmailForm {
    @NotBlank(message = "メールアドレスを入力してください")
    @Size(min = 1, max = 256, message = "メールアドレスは1~256文字で入力してください")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "メールアドレスの形式が不正です")
    private String email;
}
