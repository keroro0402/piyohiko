package com.example.api.form;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MtTopForm{
    @Size(min = 1, max = 32, message = "1~32文字で入力してください")
    private String name;
    @Size(min = 1, max = 64, message = "1~64文字で入力してください")
    private String catchPhrase;
}
