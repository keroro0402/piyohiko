package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto createUser(int id, String name){
        int age = 20;
        return new UserDto(id, name, age);
    }
}
