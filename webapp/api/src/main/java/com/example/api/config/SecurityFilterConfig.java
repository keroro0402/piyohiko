package com.example.api.config;

import com.example.api.security.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterConfig {

    private final JwtFilter jwtFilter;
    public SecurityFilterConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF（Cross-Site Request Forgery）保護を無効化
                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/users").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .anonymous(anon -> anon.disable())
                /*
                * Spring Security が使う "/login" を使えないようにする(エンドポイントで /login が使えるようにする)
                * これがないとSecurityFilterChain が /login を使うので Controller で使えない
                * */
                .formLogin(form -> form.disable());
        return http.build();
    }
}
