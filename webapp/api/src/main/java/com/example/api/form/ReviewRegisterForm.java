package com.example.api.form;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReviewRegisterForm {
    private Integer restaurantId;
    private String userId;
    private LocalDate visitDate;
    private Integer rating;
    private String comment;
}
