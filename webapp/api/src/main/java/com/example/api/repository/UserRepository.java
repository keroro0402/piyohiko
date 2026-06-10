package com.example.api.repository;

import com.example.api.entity.PasswordResetRequest;
import com.example.api.entity.User;

public interface UserRepository {
    User findByEmail(String email);
    void createUser(User user);
    void save(User user);
}
