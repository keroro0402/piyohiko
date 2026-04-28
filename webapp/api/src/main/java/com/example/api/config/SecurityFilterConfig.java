package com.example.api.config;

import com.example.api.security.JwtFilter;
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
                .csrf(csrf -> csrf.disable()) // API専用のため、CSRF保護を無効化
                .anonymous(anon -> anon.disable()) // 未認証ユーザーを許さないため、「匿名ユーザー」として扱う機能を無効化
                .formLogin(form -> form.disable()) // ログイン画面は自作のため、デフォルトのログインフォーム使用を無効化
                .httpBasic( basic -> basic.disable()) // より強化なトークン認証を使うため、Basic認証を無効化
                // リクエストに対して、どのページにアクセスさせるかを設定
                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
                                .requestMatchers("/login").permitAll() // 全リクエストでアクセス許可
                                .requestMatchers("/admin").hasRole("ADMIN") // roleに ADMIN があれば許可
                                .requestMatchers("/users").hasRole("USER") // roleに USER があれば許可
                                .anyRequest().authenticated() // 上記以外のページへアクセスにはログイン必須を宣言
                )
                .cors(Customizer.withDefaults()) // CORSの設定を反映
                // JWT認証フィルターを、標準のユーザー名/パスワード認証の前に実行するよう設定（認証開始前にJWT認証フィルターをかけたいため）
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        // これまでの設定をすべて統合して、SecurityFilterChainの実体を作成して返却する
        return http.build();
    }
}
