package com.example.api.sandbox.repository;

import com.example.api.entity.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    List<Restaurant> selectByNameWildcard(String restaurantName);
}
