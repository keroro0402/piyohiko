package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.NoConstructorYesNoArgs1Dto;
import com.example.api.sandbox.service.NoConstructorYesNoArgs1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NoConstructorYesNoArgs1Controller {
    private final NoConstructorYesNoArgs1Service noConstructorYesNoArgs1Service;
    @GetMapping("/no_constructor_yes_noargs1")
    public NoConstructorYesNoArgs1Dto getNoConstructorYesArgs(){
        return noConstructorYesNoArgs1Service.getNoConstructorYesArgs();
    }
}
