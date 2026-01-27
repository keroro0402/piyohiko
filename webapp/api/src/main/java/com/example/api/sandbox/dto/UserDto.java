package com.example.api.sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // 自分でnewする。引数必須。JSON デシリアライズで使われない。
@NoArgsConstructor // Jackson が JSON から DTO を生成するときに使う。引数不要。JSON デシリアライズで使う。
public class UserDto {
    private int id;
    private String name;
    private int age;
}
