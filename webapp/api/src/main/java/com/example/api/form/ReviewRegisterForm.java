package com.example.api.form;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReviewRegisterForm {
    @NotNull(message = "必須入力です")
    @Min(value = 1, message = "正の整数を入力してください")
    private Integer restaurantId;
    @Size(min = 4, max = 16, message = "4~6文字で入力してください")
    private String userId;
    @NotNull(message = "必須入力です")
    @Past(message = "今日以前の日付を入力してください")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate visitDate;
    @NotNull(message = "必須入力です")
    @Min(value = 1, message = "1~5で入力してくだい")
    @Max(value = 5, message = "1~5で入力してくだい")
    private Integer rating;
    @Size(min = 1, max = 500, message = "1~500文字で入力してください")
    private String comment;
}
