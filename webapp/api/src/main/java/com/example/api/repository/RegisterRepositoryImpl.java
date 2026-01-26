package com.example.api.repository;

import com.example.api.entity.Review;
import org.springframework.stereotype.Service;

@Service
public class RegisterRepositoryImpl implements RegisterRepository{
    @Override
    public void add(Review review){
        System.out.println("--登録--");
        System.out.println(review);
    }
}
