package com.example.api.service;

import com.example.api.entity.MtTop;
import com.example.api.repository.MtTopRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MtTopServiceImpl implements MtTopService{

    private final MtTopRepositoryImpl mtTopRepositoryImpl;

    @Override
    public void register(MtTop mttop){
        mtTopRepositoryImpl.add(mttop);
    }
}
