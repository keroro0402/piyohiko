package com.example.api.service;

import com.example.api.entity.User;

public interface LoginSampleService {
    User login(String loginId, String password);
}
