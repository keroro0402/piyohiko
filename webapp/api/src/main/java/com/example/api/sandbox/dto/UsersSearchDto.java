package com.example.api.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class UsersSearchDto {
    private int id;
    private String name;
    private String email;
}
