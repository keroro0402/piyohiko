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
    /*
    * asswordEncoder という型で、BCryptアルゴリズムを使う実体を返却
    * */
    public PasswordEncoder passwordEncoder(){
        /*
        * BCryptPasswordEncoder：パスワード専用の安全な変換用クラス
        * */
        return new BCryptPasswordEncoder(); // BCryptアルゴリズム（ハッシュ化用クラス）を実体化
    }

}
