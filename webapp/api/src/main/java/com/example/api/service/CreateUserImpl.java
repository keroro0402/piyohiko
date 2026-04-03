package com.example.api.service;

import com.example.api.dto.UserRequestDto;
import com.example.api.entity.User;
import com.example.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserImpl implements CreateUserService{

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public void createUser(UserRequestDto dto) {

        String encodedPassword =  passwordEncoder.encode(dto.getPassword());

        User user = new User();
        user.setLoginId(dto.getLoginId());
        user.setPassword(encodedPassword);

        userRepository.save(user);

    }
}
