package com.example.api.repository;

import com.example.api.entity.UserSample;

public interface UserSampleRepository {
    UserSample findByLoginId(String loginId);
}
