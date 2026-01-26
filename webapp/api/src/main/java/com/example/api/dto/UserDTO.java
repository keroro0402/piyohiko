package com.example.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String name;
    private int minAge;
    private int maxAge;
    private Boolean active;

    public UserDTO(String name, Integer minAge, Integer maxAge, Boolean active){
        this.name = name;
        this.minAge = (minAge == null)  ? 0 : minAge;
        this.maxAge = (maxAge == null)  ? 100 : maxAge;
        this.active = (active == null)  ? true : active;
    }
}
