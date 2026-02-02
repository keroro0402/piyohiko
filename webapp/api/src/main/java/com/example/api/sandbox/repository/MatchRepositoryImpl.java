package com.example.api.sandbox.repository;

import com.example.api.sandbox.dto.MatchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@RequiredArgsConstructor
@Repository
public class MatchRepositoryImpl implements MatchRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void add(MatchDto dto){
        String sql = "INSERT INTO `test_db`.`m_match` (date, place, max_players) VALUES (?,?,?)";
        jdbcTemplate.update(
                sql,
                dto.getDate(),
                dto.getPlace(),
                dto.getMaxPlayers());
        System.out.println("登録しました");
    }
    @Override
    public boolean existByDateAndPlace(LocalDate date, String place){
        String sql = "SELECT COUNT(*) FROM `test_db`.`m_match` WHERE date = ? AND place = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, date, place);
        return count != null && count > 0;
    }
}
