package com.example.api.repository;

import com.example.api.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository{
    @Override
    public void add(Review review){
        System.out.println("--登録--");
        System.out.println(review);
    }
}
