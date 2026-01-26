package com.example.api.mock;

import com.example.api.service.RegisterService;

public class RegisterServiceMock implements RegisterService {
    @Override
    public String regist(){
        return "仮登録";
    }
}
