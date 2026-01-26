package com.example.api.service;

import com.example.api.dto.CryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CryService {
    public List<CryDTO> getCry(int id){

        final CryDTO p1 = new CryDTO(1, "Cry1");
        final CryDTO p2 = new CryDTO(2, "Cry2");
        final CryDTO p3 = new CryDTO(3, "Cry3");
       return List.of(p1, p2, p3).stream().filter(item -> item.getId() == id).toList();
    }
}
