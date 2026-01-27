package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.Hello2Dto;
import com.example.api.sandbox.service.Hello2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Hello2Controller {

//    private final Hello2Service hello2Service;

//    @GetMapping("/hello2")
    public Hello2Dto getHello2(
            @RequestParam(defaultValue = "hello2") String message,
            @RequestParam String username
    ){
        return hello2Service.getHello2(message, username);
    }
}
