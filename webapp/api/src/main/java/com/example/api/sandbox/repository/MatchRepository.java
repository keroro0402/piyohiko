package com.example.api.sandbox.repository;
import com.example.api.sandbox.dto.MatchDto;
import java.time.LocalDate;


public interface MatchRepository {
    void add(MatchDto dto);
    boolean existByDateAndPlace(LocalDate date, String place);
}
