package com.example.api.repository;

import com.example.api.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    /*JdbcTemplate:SQLを書くためのクラス*/
    private final JdbcTemplate jdbcTemplate;

    @Override
    public User findByLoginId(String loginId) {
        String sql = "SELECT        " +
                        "user_id,   " +
                        "login_id,, " +
                        "password,  " +
                     "FROM          " +
                        "t_user     " +
                     "WHERE         " +
                        "login_id = ?";
        /*queryForObject：DB照合して 1個のオブジェクト を得るメソッド
        * 引数は SQL、RowMapper、SQLに渡す値 の順に3つで固定
        * */
        return jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> {
                    User user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setLoginId(rs.getString("login_id"));
                    user.setPassword(rs.getString("password"));
                    return user;
                }, loginId
        );
    }
}
