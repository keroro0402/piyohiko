package com.example.api.service;

import com.example.api.entity.Review;
import com.example.api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final ReviewRepository repository;

    @Override
    public void register(Review review){
        repository.add(review);
    }
}
