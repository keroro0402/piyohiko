package com.example.api.sandbox.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TeamForm {
    @NotBlank(message = "必須入力です")
    private String name;
}
