package com.example.api.service;

import com.example.api.form.SignUpForm;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService{
    @Override
    public String signUp(SignUpForm signUpForm){
        System.out.println("*******************");
        System.out.println(signUpForm.getLoginId());
        System.out.println(signUpForm.getPassword());
        System.out.println("*******************");
    return signUpForm + "がAPIに渡されました";
    }
}
