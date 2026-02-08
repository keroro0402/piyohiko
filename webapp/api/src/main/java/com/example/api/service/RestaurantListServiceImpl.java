package com.example.api.service;

import com.example.api.entity.Restaurant;
import com.example.api.sandbox.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantListServiceImpl implements RestaurantListService{

    private final RestaurantRepository repository;

    @Override
    public List<Restaurant> findByNameWildcard(String restaurantName){
        List<Restaurant> list = repository.selectByNameWildcard(restaurantName);
        return list;
    }
}
