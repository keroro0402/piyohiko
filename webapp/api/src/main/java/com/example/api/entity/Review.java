package com.example.api.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Review {
    private Integer restaurantId;
    private String userId;
    private LocalDate visitDate;
    private Integer rating;
    private String comment;
}
