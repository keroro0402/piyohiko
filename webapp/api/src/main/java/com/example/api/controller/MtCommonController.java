package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MtCommonController {
    @GetMapping("/mt-complete")
    public String redirect(){
        return "mt-complete";
    }
}
