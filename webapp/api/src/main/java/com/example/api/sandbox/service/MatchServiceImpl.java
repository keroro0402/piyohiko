package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.MatchDto;
import com.example.api.sandbox.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchServiceImpl implements MatchService{

    private final MatchRepository matchRepository;
    @Override
    public void register(MatchDto dto){
        boolean flg = matchRepository.existByDateAndPlace(dto.getDate(), dto.getPlace());

        if(flg){
            throw new IllegalStateException("同じ日時、場所が登録されています。登録不可です。");
        }

        matchRepository.add(dto);
    }
}
