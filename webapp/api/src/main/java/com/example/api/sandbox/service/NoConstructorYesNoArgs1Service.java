package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.NoConstructorNoNoArgs1Dto;
import com.example.api.sandbox.dto.NoConstructorYesNoArgs1Dto;
import com.example.api.sandbox.dto.NoConstructorYesNoArgs1Status;
import org.springframework.stereotype.Service;

@Service
public class NoConstructorYesNoArgs1Service {
    private NoConstructorYesNoArgs1Dto noConstructorYesNoArgs1Dto;

    public NoConstructorYesNoArgs1Dto getNoConstructorYesArgs(){
        NoConstructorYesNoArgs1Dto dto = new NoConstructorYesNoArgs1Dto();
        dto.setStatus(NoConstructorYesNoArgs1Status.MEMBER_EMAIL);
        return dto;
    }
}
