package com.example.api.repository;

import com.example.api.entity.UserSample;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserSampleRepositoryImpl implements UserSampleRepository {
    /*JdbcTemplate:SQLを書くためのクラス*/
    private final JdbcTemplate jdbcTemplate;

    @Override
    public UserSample findByLoginId(String loginId) {
        String sql = "SELECT        " +
                        "user_id,   " +
                        "login_id, " +
                        "password  " +
                     "FROM          " +
                        "t_user     " +
                     "WHERE         " +
                        "login_id = ?";
        /*queryForObject：DB照合して 1個のオブジェクト を得るメソッド
        * 引数は SQL、RowMapper、SQLの?に渡す値 の順
        * RowMapper：DBの1行（ResultSet）をUser オブジェクトに変換する方法
        * 引数は rs（DBの1レコード）、rowNum（結果セット内の行番号。通常は使わない）の順に2つで固定
        * */
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    UserSample userSample = new UserSample();
                    userSample.setUserId(rs.getInt("user_id"));
                    userSample.setLoginId(rs.getString("login_id"));
                    userSample.setPassword(rs.getString("password"));
                    return userSample;
                }, loginId
        );
    }
}
