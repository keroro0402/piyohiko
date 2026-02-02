package com.example.api.sandbox.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;


@Setter
@Getter
public class MatchDto {
    private LocalDate date;
    private  String place;
    private  int maxPlayers;
}
