package com.example.api.service;

import com.example.api.entity.User;
import com.example.api.exception.LoginException;
import com.example.api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{
    private final UserRepository userRepository;

    public LoginServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User login(String loginId, String password) {

        User user = userRepository.findByLoginId(loginId);

        if(!user.getPassword().equals(password)){
            throw new LoginException("ログイン失敗");
        }

        return user;
    }

}
