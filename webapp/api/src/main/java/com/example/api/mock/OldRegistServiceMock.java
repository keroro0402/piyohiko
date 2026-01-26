package com.example.api.mock;

import com.example.api.service.RegistService;

public class OldRegistServiceMock implements RegistService {
    @Override
    public String regist(){
        return "仮登録";
    }
}
