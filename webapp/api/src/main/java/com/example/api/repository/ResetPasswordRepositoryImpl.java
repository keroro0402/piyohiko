package com.example.api.repository;

import com.example.api.entity.ResetPassword;
import com.example.api.entity.UserSample;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ResetPasswordRepositoryImpl implements ResetPasswordRepository{
    private final JdbcTemplate jdbcTemplate;

    // t_password_reset_request から条件に合致する一意のレコード or null を取得する
    @Override
    public Optional<ResetPassword> findByUserIdAndSecretCodeAndIsUsedFalseAndExpiryDateAfter(Integer userId,
                                                                                            String secretCode,
                                                                                            LocalDateTime now) {

        String sql = """
               SELECT
                   id,
                   user_id,
                   secret_code,
                   expiry_date,
                   is_used
               FROM
                   t_password_reset_request
               WHERE user_id = ? AND secret_code = ?      -- アカウントとコードの一致を確認
               AND
                    is_used = false                     -- 未使用なこと（一回きり）
               AND
                    expiry_date > ?;                     -- 期限内なこと
               """;
        try {
            ResetPassword resetPassword = jdbcTemplate.queryForObject(
                    sql,
                    (rs, rowNum) -> new ResetPassword(
                            // ResetPassword はrecord型で不変（Immutable）なため、コンストラクタで全値を直接指定して生成する
                            rs.getLong("id"),
                            rs.getInt("user_id"),
                            rs.getString("secret_code"),
                            rs.getTimestamp("expiry_date").toLocalDateTime(),
                            rs.getBoolean("is_used")
                    ),
                    userId, secretCode, now // ? に入る引数を最後に並べる
            );

            System.out.println("********");
            System.out.println(resetPassword);
            System.out.println("********");
            return Optional.ofNullable(resetPassword); // 引数が null だったら空っぽの箱を自動で作るし、値が入っていたらその値を中に詰めた箱を作る

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty(); // 条件に合うレコードが0件（未登録・期限切れなど）の場合は空を返す
        }

    }

    // t_password_reset_request の is_used を更新する
    @Override
    public void updateIsUsedToTrue(Long id) {
        String sql = "UPDATE t_password_reset_request SET is_used = 1 WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
