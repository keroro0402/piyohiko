package com.example.api.mock;

import com.example.api.dto.TopicDto;
import com.example.api.repository.RegisterRepository;
import com.example.api.service.RegisterService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterServiceMock implements RegisterService {
    private final RegisterRepository repository;
    @Override
    public void register(TopicDto topic) {
        repository.add(topic);
    }
}