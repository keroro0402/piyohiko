package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.YesConstructorNoNoArgs1Dto;
import com.example.api.sandbox.dto.YesConstructorNoNoArgs1Status;
import org.springframework.stereotype.Service;

@Service
public class YesConstructorNoNoArgs1Service {
    private YesConstructorNoNoArgs1Dto yesConstructorNoNoArgs1Dto;

    public YesConstructorNoNoArgs1Dto getStatus(){
        YesConstructorNoNoArgs1Dto dto = new YesConstructorNoNoArgs1Dto(YesConstructorNoNoArgs1Status.MEMBER_NUMBER);
        return dto;
    }
}
