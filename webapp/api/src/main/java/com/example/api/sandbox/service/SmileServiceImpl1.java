package com.example.api.sandbox.service;

import org.springframework.stereotype.Service;

@Service("type1")
public class SmileServiceImpl1 implements SmileService{
    @Override
    public String getSmile(){
        return "Smile1";
    }
}
