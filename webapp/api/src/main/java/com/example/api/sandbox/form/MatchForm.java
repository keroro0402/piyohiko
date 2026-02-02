package com.example.api.sandbox.form;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MatchForm {
    @NotNull(message = "必須入力です")
    @Future(message = "今日以降を指定してください")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;
    @NotBlank(message = "必須入力です")
    private  String place;
    @Min(value = 1 , message = "1より上の整数で入力してください")
    private int maxPlayers;
}
