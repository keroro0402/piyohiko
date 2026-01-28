package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.UsersDto;
import com.example.api.sandbox.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UsersController {
    private final UsersService usersService;
    @GetMapping("/users")
    public List<UsersDto> getUsers(
    ){
        return usersService.getUsers();
    }
}
