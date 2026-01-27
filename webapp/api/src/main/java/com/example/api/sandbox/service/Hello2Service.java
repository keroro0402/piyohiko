package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.Hello2Dto;
import org.springframework.stereotype.Service;

//@Service
public class Hello2Service {
    public Hello2Dto getHello2(String message, String username){
        return new Hello2Dto(message, username);
    }
}
