package com.example.api.sandbox.service;

import com.example.api.sandbox.dto.UsersSearchDto;
import com.example.api.sandbox.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersSearchService {
    public UsersSearchDto serchUser(int id){
        UsersSearchDto user1 = new UsersSearchDto(1,"user1", "test@test.com");
        UsersSearchDto user2 = new UsersSearchDto(2,"user2", "test@test.com");
        UsersSearchDto user3 = new UsersSearchDto(3,"user3", "test@test.com");
        List<UsersSearchDto> list = List.of(user1, user2, user3);
        /*filter版*/
//        UsersSearchDto result =
//                list.stream()
//                .filter(item -> item.getId() == id)
//                .findFirst().orElseThrow(() -> new UserNotFoundException(id));
//        return result;

        /*for版*/
//        for (UsersSearchDto user : list){
//            if(user.getId() == id){
//                return user;
//            }
//        }
        /*for版(continue)*/
        for (UsersSearchDto user : list){
            if(user.getId() != id){
                continue;
            }
            return user;
        }

        throw new UserNotFoundException(id);
    }

}
