package com.example.api.service;

import com.example.api.entity.UserSample;

public interface LoginSampleService {
    UserSample login(String loginId, String password);
}
