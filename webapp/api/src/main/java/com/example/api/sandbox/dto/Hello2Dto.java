package com.example.api.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // GET用
@NoArgsConstructor // POST用
public class Hello2Dto {

    private String message;
    private String username;
}
