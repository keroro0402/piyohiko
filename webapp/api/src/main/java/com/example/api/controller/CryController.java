package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CryController {
    @GetMapping("/cry")
    public String cry(Model model){
        model.addAttribute("title", "泣きたい");
        return "cry";
    }
}
