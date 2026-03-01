package com.example.api.repository;

import com.example.api.entity.Review;

import java.util.List;

public interface ReviewRepository {
    void add(Review review);
    List<Review> selectByRestaurantId(int restaurantId);
}
