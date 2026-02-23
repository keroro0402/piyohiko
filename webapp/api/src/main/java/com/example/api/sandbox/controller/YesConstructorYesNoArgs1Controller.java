package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.YesConstructorYesNoArgs1Dto;
import com.example.api.sandbox.service.YesConstructorYesNoArgs1Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class YesConstructorYesNoArgs1Controller {
    private final YesConstructorYesNoArgs1Service yesConstructorYesNoArgs1Service;
    @GetMapping("/yes_constructor_yes_noargs1")
    public YesConstructorYesNoArgs1Dto getStatus(){
        return yesConstructorYesNoArgs1Service.getStatus();
    }
}
