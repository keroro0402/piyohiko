package com.example.api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwUtil {
    private static final String SECRET_KEY = "my-super-secret-key-that-is-very-long-123412256";

    public static String generateToken(String loginId){
        SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)); // 文字列の秘密鍵を署名用のSecretKeyに変換
        return Jwts.builder()  // トークンの土台作成
                .setSubject(loginId)  // ユーザー識別子（Subject）を設定
                .setIssuedAt(new Date())  // 発行日時（IssuedAt）を設定
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))  // 有効期限（Expiration）を設定
                .signWith(key)  // 秘密鍵で署名（改ざん防止）
                .compact();  // JWTを生成して文字列として返す
    }
}
