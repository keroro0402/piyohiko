package com.example.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    // application.ymlから秘密鍵を注入
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private SecretKey key;

    @PostConstruct
    public void init(){
        this.key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)); // 文字列の秘密鍵を署名用のSecretKeyに変換
    }

    public String generateToken(String email, String role, long expiration){
        return Jwts.builder()  // トークンの土台作成
                .setSubject(email)  // ユーザー識別子をSubjectに設定
                .claim("role", role)  // 権限をclaimに設定
                .setIssuedAt(new Date())  // 発行日時をIssuedAtに設定
                .setExpiration(new Date(System.currentTimeMillis() + expiration))  // 有効期限をExpirationに設定
                .signWith(this.key)  // 秘密鍵で署名（改ざん防止）
                .compact();  // JWTを生成して文字列として返す
    }

    public String getLoginIdFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
