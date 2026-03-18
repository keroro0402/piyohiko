package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*
* @Configuration：設定クラス用アノテーション
* */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        /*
        * BCryptPasswordEncoder：パスワード専用の安全な変換用クラス
        * */
        return new BCryptPasswordEncoder();
    }

}
