package com.example.api.service;

import com.example.api.entity.Review;

import java.util.List;

public interface ReviewListService {
    List<Review> findByRestaurantId(int restaurantId);
}
