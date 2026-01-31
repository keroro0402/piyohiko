package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.UsersSearchDto;
import com.example.api.sandbox.service.UsersSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UsersSearchController {

    private final UsersSearchService usersSearchService;

    @GetMapping("/users/search")
    public UsersSearchDto getUser(
            @RequestParam int id
    ){
        return usersSearchService.searchUser(id);
    }
}
