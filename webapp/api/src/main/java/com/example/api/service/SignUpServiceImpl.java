package com.example.api.service;

import com.example.api.dto.SignUpDto;
import com.example.api.entity.User;
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
        String securityPhrase = signUpDto.getSecurityPhrase();
        User user = new User();
        user.setRole("ROLE_USER");
        // roleを securityPhrase と SECURITY_SUBJECT が一致したら "ROLE_ADMIN" に上書き
        if(securityPhrase != null && securityPhrase.equals(SECURITY_SUBJECT)){
            user.setRole("ROLE_ADMIN");
        }
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());
        user.setLoginId(signUpDto.getLoginId());
        user.setPassword(encodedPassword);
     userRepository.createUser(user);
    }
}
