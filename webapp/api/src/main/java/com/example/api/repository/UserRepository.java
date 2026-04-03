package com.example.api.repository;

import com.example.api.entity.User;

public interface UserRepository {
    User findByLoginId(String loginId);
    void save(User user);
}
