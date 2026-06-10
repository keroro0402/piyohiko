package com.example.api.repository;

import com.example.api.entity.PasswordResetRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SendPasswordResetEmailRepositoryImpl implements SendPasswordResetEmailRepository{
    private final JdbcTemplate jdbcTemplate;


    /* PWリセットメール送信レコード登録用のメソッド */
    @Override
    public void createPasswordResetRequest(PasswordResetRequest passwordResetRequest) {
        String sql = "INSERT INTO `api_db`.`t_password_reset_token` (user_id, token, expiry_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                // passwordResetToken はレコードクラスで設計してあるので、メソッド名からgetが消えている
                passwordResetRequest.userId(),
                passwordResetRequest.token(),
                passwordResetRequest.expiryDate()
                // テーブル作成時に DEFAULT で指定済みなのでis_used、created_atは省略
        );
    }


}
