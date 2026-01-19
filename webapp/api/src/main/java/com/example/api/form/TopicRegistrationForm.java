package com.example.api.form;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class TopicRegistrationForm {
    @NotNull(message = "入力してください")
    private Integer registrationId;
    @Pattern(regexp = "\\S{4,10}", message = "4から10文字で入力してください（スペース不可）")
    private String userId;
    @NotNull(message = "入力してください")
    @Past(message = "今日以前の日付を選択してください")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate registrationDate;
    @Size(min = 1, max = 50, message = "1から50文字で入力してください（スペース可）")
    private String topicTitle;
    @NotBlank(message = "入力してください")
    private String topicContent;
}
