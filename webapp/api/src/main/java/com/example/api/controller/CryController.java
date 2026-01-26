package com.example.api.controller;

import com.example.api.dto.CryDTO;
import com.example.api.service.CryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CryController {

    private final CryService cryService;

    public CryController(CryService cryService){
        this.cryService = cryService;
    }

    @GetMapping("/cry")

    public List<CryDTO> getCry(
            @RequestParam int id
    ){
        return cryService.getCry(id);
    }


}
