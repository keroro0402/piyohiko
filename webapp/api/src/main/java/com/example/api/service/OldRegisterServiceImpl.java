package com.example.api.service;

import com.example.api.dto.TopicDto;
import com.example.api.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OldRegisterServiceImpl implements OldRegisterService {

    private final RegisterRepository repository;
    @Override
    public void register(TopicDto topic) {
        repository.add(topic);
    }
}
