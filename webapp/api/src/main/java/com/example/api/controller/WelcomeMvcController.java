package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeMvcController {
    @GetMapping("/welcome-mvc")
    public String getWelcome(Model model){
        model.addAttribute("message", "WelCome!!!");
        return "welcome-mvc";
    }
}
