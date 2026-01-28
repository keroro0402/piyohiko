package com.example.api.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UsersDto {
    private int id;
    private String name;
    private String email;

    public UsersDto(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
