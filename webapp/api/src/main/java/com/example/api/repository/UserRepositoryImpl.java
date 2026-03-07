package com.example.api.repository;

import com.example.api.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User findByLoginId(String loginId) {

        String sql = "SELECT user_id, login_id, password FROM t_user WHERE login_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setLoginId(rs.getString("login_id"));
                user.setPassword(rs.getString("password"));
                return user;
            }, loginId);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
