package com.example.api.config;

import com.example.api.security.JwtFilter;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityFilterConfig {

    private final JwtFilter jwtFilter;
    public SecurityFilterConfig(JwtFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    // CORSの設定
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        // CORSの設定情報を保持するオブジェクト（ルールブック）を生成
        CorsConfiguration config = new CorsConfiguration();
        // 許可するドメインの設定
        config.setAllowedOrigins(List.of("http://localhost:3000"));
        // 許可するヘッダーの設定
        config.setAllowedHeaders(List.of(
                "Origin",               // リクエスト元のドメイン特定のため（setAllowedOriginsを設定したら必須）
                "Content-Type",         // リクエスト（往路）のデータ形式指定を許可するため（axios使用するなら必須。application/jsonの通信を許可する）
                "Accept",               // レスポンス（復路）のデータ形式指定を許可するため（JSONを希望する等）
                "Authorization",        // JWTトークンなどの認証情報をヘッダーに載せることを許可するため
                "X-Requested-With"      // Ajax通信（axios等）であることを示す
        ));
        // 許可するメソッドの設定
        config.setAllowedMethods(List.of(
                "GET",                  // 取得を許可
                "POST",                 // 登録を許可
                "PUT",                  // 更新を許可
                "DELETE"                // 削除を許可
        ));
        // クッキーや認証情報（JWTトークン等）を含むリクエストの設定
        config.setAllowCredentials(true); // 許可
        // URLごとにどのCORS設定を適用するか管理するオブジェクトを用意
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // 「すべてのURL（/**）」に対して、作成したCORS設定（config）を適用
        source.registerCorsConfiguration("/**", config);
        return source;
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
                .cors(Customizer.withDefaults()) // CORSの設定を反映
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .anonymous(anon -> anon.disable())
                .formLogin(form -> form.disable());
        return http.build();
    }
}
