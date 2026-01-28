package com.example.api.sandbox.controller;

import com.example.api.sandbox.service.SmileService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmileController {

    private final SmileService smileService;

    public SmileController(@Qualifier("type2") SmileService smileService){
        this.smileService = smileService;
    }
    @GetMapping("smile")
    public String getSmile(){
        return smileService.getSmile();
    }

}
