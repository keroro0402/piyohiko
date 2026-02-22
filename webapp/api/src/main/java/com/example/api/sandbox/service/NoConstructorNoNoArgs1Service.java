package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.NoConstructorNoNoArgs1Dto;
import com.example.api.sandbox.dto.NoConstructorNoNoArgs1Status;
import org.springframework.stereotype.Service;

@Service
public class NoConstructorNoNoArgs1Service {

    public NoConstructorNoNoArgs1Dto getNoConstructorNoNoArgs(){
        NoConstructorNoNoArgs1Dto dto = new NoConstructorNoNoArgs1Dto(); /*ここでエラー*/
        dto.setStatus(NoConstructorNoNoArgs1Status.MEMBER_NAME);
        return dto;
    }
}
