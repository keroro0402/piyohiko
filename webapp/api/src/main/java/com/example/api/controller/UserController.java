package com.example.api.controller;

import com.example.api.dto.UserRequestDto;
import com.example.api.service.CreateUserService;
import com.example.api.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final CreateUserService createUserService;


    public UserController(CreateUserService createUserService){
        this.createUserService = createUserService;
    }


    @PostMapping("/users")
    public void createUser(@Valid @RequestBody UserRequestDto dto){
        createUserService.createUser(dto);
    }
}
