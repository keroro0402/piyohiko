package com.example.api.service;

import com.example.api.form.SignUpForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{

    private final PasswordEncoder passwordEncoder;

    @Override
    public String signUp(SignUpForm signUpForm){
        System.out.println("*******************");
        System.out.println(signUpForm.getLoginId());
        System.out.println(signUpForm.getPassword());
        System.out.println("*******************");
        String encodedPassword = passwordEncoder.encode(signUpForm.getPassword());
    return encodedPassword + "がAPIに渡されました";
    }
}
