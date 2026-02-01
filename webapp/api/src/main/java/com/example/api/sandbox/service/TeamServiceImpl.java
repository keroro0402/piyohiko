package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.TeamDto;
import com.example.api.sandbox.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{

    private final TeamRepository teamRepository;

    @Override
    public void register(TeamDto dto){
        teamRepository.add(dto);
    }
}
