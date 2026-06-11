package com.example.api.repository;

import com.example.api.entity.SendPasswordResetEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SendPasswordResetEmailRepositoryImpl implements SendPasswordResetEmailRepository{
    private final JdbcTemplate jdbcTemplate;


    /* PWリセットメール送信レコード登録用のメソッド */
    @Override
    public void createPasswordResetRequest(SendPasswordResetEmail sendPasswordResetEmail) {
        String sql = "INSERT INTO t_password_reset_request (user_id, secret_code, expiry_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                // sendPasswordResetEmail はレコードクラスで設計してあるので、メソッド名にgetは付かない
                sendPasswordResetEmail.userId(),
                sendPasswordResetEmail.secretCode(),
                sendPasswordResetEmail.expiryDate()
                // テーブル作成時に DEFAULT で指定済みなのでis_used、created_atは省略
        );
    }


}
