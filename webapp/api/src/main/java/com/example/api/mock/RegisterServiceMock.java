package com.example.api.mock;

import com.example.api.service.RegisterService;

public class RegisterServiceMock implements RegisterService {
    @Override
    public String register() {
        return "テスト完了";
    }
}