package com.example.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilterConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF（Cross-Site Request Forgery）保護を無効化
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() // Controller の /login 使用を許可（これを入れないと Security が勝手にブロックする）
                        .anyRequest().authenticated() // "/login" 以外の URL は ログイン済み（認証済み）でなければアクセス不可
                )
                /*
                * Spring Security が使う "/login" を使えないようにする(エンドポイントで /login が使えるようにする)
                * これがないとSecurityFilterChain が /login を使うので Controller で使えない
                * */
                .formLogin(form -> form.disable());
        return http.build();
    }
}
