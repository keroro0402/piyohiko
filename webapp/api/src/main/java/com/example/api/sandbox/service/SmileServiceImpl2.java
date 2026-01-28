package com.example.api.sandbox.service;

import org.springframework.stereotype.Service;

@Service("type2")
public class SmileServiceImpl2 implements SmileService{
    @Override
    public String getSmile(){
        return "Smile2";
    }
}
