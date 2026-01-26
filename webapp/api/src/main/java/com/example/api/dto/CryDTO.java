package com.example.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CryDTO {
    private final int id;
    private final String cry;

//    public CryDTO(int id, String cry){
//        this.id = id;
//        this.cry = cry;
//    }
}
