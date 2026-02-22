package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.NoConstructorNoNoArgs1Dto;
import com.example.api.sandbox.service.NoConstructorNoNoArgs1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoConstructorNoNoArgs1Controller {
    private final NoConstructorNoNoArgs1Service noConstructorNoNoArgs1Service;
    @GetMapping("/no_constructor_no_noargs1")
    public NoConstructorNoNoArgs1Dto getNoConstructorNoNoArgs(){
        return noConstructorNoNoArgs1Service.getNoConstructorNoNoArgs();
    }
}
