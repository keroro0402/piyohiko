package com.example.api.service;

import com.example.api.entity.Restaurant;

import java.util.List;

public interface RestaurantListService {
    List<Restaurant> findByNameWildcard(String restaurantName);
}
