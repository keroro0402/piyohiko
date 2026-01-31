package com.example.api.repository;

import com.example.api.entity.MtTop;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MtTopRepositoryImpl implements MtTopRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void add(MtTop mttop){
        String sql = "INSERT INTO mt_top (name, catchPhrase) VALUES (?, ?)";
        jdbcTemplate.update(sql, mttop.getName(), mttop.getCatchPhrase());
    }
}
