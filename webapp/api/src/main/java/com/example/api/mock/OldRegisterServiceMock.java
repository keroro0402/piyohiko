package com.example.api.mock;

import com.example.api.dto.TopicDto;
import com.example.api.repository.RegisterRepository;
import com.example.api.service.OldRegisterService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OldRegisterServiceMock implements OldRegisterService {
    private final RegisterRepository repository;
    @Override
    public void register(TopicDto topic) {
        repository.add(topic);
    }
}