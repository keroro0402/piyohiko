package com.example.api.service;

import com.example.api.dto.SignUpDto;
import com.example.api.entity.User;
import com.example.api.exception.DuplicateUserException;
import com.example.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String SECURITY_SUBJECT = "YES_ADMIN";

    @Override
    public void signUp(SignUpDto signUpDto){

        User registeredUser = userRepository.findByLoginId(signUpDto.getLoginId());
        // 重複するログインID がリクエストされたなら GlobalExceptionHandler の handleRegisterException に入る
        if(registeredUser != null){
            throw new DuplicateUserException("REGISTER_FAILED", "登録済みのログインIDでリクエストされました");
        }

        String securityPhrase = signUpDto.getSecurityPhrase();
        User newUser = new User();
        newUser.setRole("ROLE_USER");
        // role を securityPhrase と SECURITY_SUBJECT が一致したら "ROLE_ADMIN" に上書き
        if(securityPhrase != null && securityPhrase.equals(SECURITY_SUBJECT)){
            newUser.setRole("ROLE_ADMIN");
        }
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        newUser.setLoginId(signUpDto.getLoginId());
        newUser.setPassword(encodedPassword);
     userRepository.createUser(newUser);
    }
}
