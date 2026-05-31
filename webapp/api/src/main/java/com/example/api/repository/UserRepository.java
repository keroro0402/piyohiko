package com.example.api.repository;

import com.example.api.entity.User;

public interface UserRepository {
    User findByLoginId(String loginId);
    void createUser(User user);
    void save(User user);
}
