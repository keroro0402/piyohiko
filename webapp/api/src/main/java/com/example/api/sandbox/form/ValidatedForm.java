package com.example.api.sandbox.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ValidatedForm {
    @NotBlank(message = "必須です")
    private String name;
}
