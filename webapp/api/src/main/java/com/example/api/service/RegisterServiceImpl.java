package com.example.api.service;

import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Override
    public String regist(){
        return "おめでとう！登録完了です！！！";
    }
}
