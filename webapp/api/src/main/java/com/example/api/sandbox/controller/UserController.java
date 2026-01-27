package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.UserDto;
import com.example.api.sandbox.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

//    private final UserService userService;

    /*@NoArgsConstructor*/
    @PostMapping("/user")
    public UserDto postUser(@RequestBody UserDto user){ // ここでUserDto(){}コンストラクタをnewしてる
        // 加工が必要なのでセッターを使う
        user.setName(user.getName() + "さん");
        // JSON デシリアライズされたDTO返却
        return user;
    }

    /*@AllArgsConstructor*/
    @GetMapping("/user/fixed")
    public UserDto getUser(
            @RequestParam int id,
            @RequestParam String name
    ){
        // インスタンス化に引数が必要
        return userService.createUser(id, name);
    }
}
