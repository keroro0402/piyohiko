package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.UsersDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    public List<UsersDto> getUsers(){
        UsersDto user1 = new UsersDto(1, "user1", "user1@test.co.jp");
        UsersDto user2 = new UsersDto(2, "user2", "user2@test.co.jp");
        UsersDto user3 = new UsersDto(3, "user3", "user3@test.co.jp");
        List<UsersDto> dto = List.of(user1, user2, user3);

        System.out.println(dto);

        return dto;
    }
}
