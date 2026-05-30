package com.example.api.service;

import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService{
    @Override
    public String signUp(){
        System.out.println("ssasasaaaa");
    return "SignUpが実行されました";
    }
}
