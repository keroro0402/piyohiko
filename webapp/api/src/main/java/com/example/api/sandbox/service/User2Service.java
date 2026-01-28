package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.User2Dto;
import org.springframework.stereotype.Service;

@Service
public class User2Service {
    public User2Dto getUser(int id){
        String name = "USER";
        String email = "user@test.com";
        User2Dto dto = new User2Dto(id, name, email);
        return dto;
    }
}
