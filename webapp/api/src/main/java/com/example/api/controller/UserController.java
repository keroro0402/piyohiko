package com.example.api.controller;

import com.example.api.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @GetMapping("/users/search")
    public UserDTO getUser(
            @RequestParam String name,
            @RequestParam Integer minAge,
            @RequestParam Integer maxAge,
            @RequestParam Boolean active
    ){
        return new UserDTO(name, minAge, maxAge, active);
    }

    @PostMapping("/user/search")
    public UserDTO postDTO(
            @RequestBody UserDTO userDTO
    ){
        UserDTO dto = new UserDTO(userDTO.getName(),userDTO.getMinAge(),userDTO.getMaxAge(),userDTO.getActive());

        return dto;
    }

}
