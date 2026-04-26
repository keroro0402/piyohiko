package com.example.api.config;

import com.example.api.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // SpringコンテナにBean登録するクラスであることを宣言
@EnableWebSecurity // Spring Securityの機能、、カスタム設定を有効化
public class BaseConfig {
    private final JwtFilter jwtFilter;

    public BaseConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

//    @Bean
    public SecurityFilterChain baseSecurityFilterChain(HttpSecurity http) throws Exception {
        // 1. フォームログインなどの標準機能をオフにする
        http
                .csrf(csrf -> csrf.disable()) // CSRF対策機能をOFF（CSRF対策でリクエストにトークンがないとエラーを返す）
                .formLogin(form -> form.disable()) // デフォルトのログインフォームを使わない
                .httpBasic(basic -> basic.disable()); // Basic認証お使わない

        // 2. セッションをステートレスにする（APIサーバーの基本）
        http.sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // 3. アクセス権限の設定
        http.authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login2").permitAll()
//                .requestMatchers("/login").permitAll() // ログインは誰でもOK
//                .anyRequest().authenticated()          // それ以外はハンコ（認証）が必要
        );

        // 4. 自作のJWTフィルタを「関所」として差し込む
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
