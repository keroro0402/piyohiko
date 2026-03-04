package com.example.api.repository;

import com.example.api.entity.User;

public interface UserSampleRepository {
    User findByLoginId(String loginId);
}
