package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.User2Dto;
import com.example.api.sandbox.service.User2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class User2Controller {
    private final User2Service user2Service;

    @GetMapping("/users/{id}")
    public User2Dto getUser(
            @PathVariable int id
    ){
        return user2Service.getUser(id);
    }
}
