package com.example.api.service;

import com.example.api.entity.User;
import com.example.api.form.SignUpForm;
import com.example.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(SignUpForm signUpForm){
        System.out.println("*******************");
        System.out.println(signUpForm.getLoginId());
        System.out.println(signUpForm.getPassword());
        System.out.println("*******************");
        String encodedPassword = passwordEncoder.encode(signUpForm.getPassword());
        User user = new User();
        user.setLoginId(signUpForm.getLoginId());
        user.setPassword(encodedPassword);
     userRepository.createUser(user);
    }
}
