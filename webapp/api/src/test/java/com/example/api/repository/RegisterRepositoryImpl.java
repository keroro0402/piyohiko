package com.example.api.repository;

import com.example.api.dto.TopicDto;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterRepositoryImpl implements RegisterRepository {
    @Override
    public void add(TopicDto topic){
        System.out.println("--登録--");
        System.out.println(topic);
    }
}
