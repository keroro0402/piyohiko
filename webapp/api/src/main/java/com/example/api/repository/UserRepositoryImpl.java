package com.example.api.repository;

import com.example.api.entity.PasswordResetRequest;
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
    public User findByEmail(String email) {

        String sql = "SELECT user_id, email, password, role FROM t_user WHERE email = ?";

        try {
            return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                return user;
            }, email);
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

/* 新規ユーザー登録用のメソッド */
    @Override
    public void createUser(User user){
        String sql = "INSERT INTO `api_db`.`t_user` (email, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql,
                user.getEmail(),
                user.getPassword(),
                user.getRole()
                );
    }


    @Override
    public void save(User user) {
        String sql = "INSERT INTO `api_db`.`t_user` (email, password) VALUES (?,?)";
         jdbcTemplate.update(sql,
                 user.getEmail(),
                 user.getPassword()
                 );
    }
}
