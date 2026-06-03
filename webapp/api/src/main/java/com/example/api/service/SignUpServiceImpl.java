package com.example.api.service;

import com.example.api.dto.SignUpRequestDto;
import com.example.api.dto.SignUpResponseDto;
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
    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto){

        User registeredUser = userRepository.findByLoginId(signUpRequestDto.getLoginId());
        // 重複するログインID がリクエストされたなら GlobalExceptionHandler の handleRegisterException に入る
        if(registeredUser != null){
            throw new DuplicateUserException("REGISTER_FAILED", "登録済みのログインIDでリクエストされました");
        }

        String securityPhrase = signUpRequestDto.getSecurityPhrase();
        User newUser = new User();
        newUser.setRole("ROLE_USER");
        // role を securityPhrase と SECURITY_SUBJECT が一致したら "ROLE_ADMIN" に上書き
        if(securityPhrase != null && securityPhrase.equals(SECURITY_SUBJECT)){
            newUser.setRole("ROLE_ADMIN");
        }
        String encodedPassword = passwordEncoder.encode(signUpRequestDto.getPassword());
        newUser.setLoginId(signUpRequestDto.getLoginId());
        newUser.setPassword(encodedPassword);
        userRepository.createUser(newUser);
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        signUpResponseDto.setMessage("新規登録が成功しました");
        return signUpResponseDto;

    }
}
