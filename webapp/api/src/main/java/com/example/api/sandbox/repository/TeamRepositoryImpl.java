package com.example.api.sandbox.repository;

import com.example.api.sandbox.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TeamRepositoryImpl implements TeamRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void add(TeamDto dto){
        String sql = "INSERT INTO `test_db`.`m_player` (name) VALUES (?)";
        jdbcTemplate.update(sql, dto.getName());
    }
}
