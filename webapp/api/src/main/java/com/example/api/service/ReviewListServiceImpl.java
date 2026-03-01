package com.example.api.service;

import com.example.api.entity.Review;
import com.example.api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewListServiceImpl implements ReviewListService{

    private final ReviewRepository repository;

    @Override
    public List<Review> findByRestaurantId(int restaurantId){

        List<Review> list = repository.selectByRestaurantId(restaurantId);

        return list;
    }
}
