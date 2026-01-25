package com.example.api.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ReviewRegisterForm {
    private Integer restaurantId;
    private String userId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate visitDate;
    private Integer rating;
    private String comment;
}
