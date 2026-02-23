package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.YesConstructorNoNoArgs1Dto;
import com.example.api.sandbox.service.YesConstructorNoNoArgs1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class YesConstructorNoNoArgs1Controller {
    private final YesConstructorNoNoArgs1Service yesConstructorNoNoArgs1Service;
    @GetMapping("/yes_constructor_no_noargs1")
    public YesConstructorNoNoArgs1Dto getStatus(){
        return yesConstructorNoNoArgs1Service.getStatus();
    }
}
