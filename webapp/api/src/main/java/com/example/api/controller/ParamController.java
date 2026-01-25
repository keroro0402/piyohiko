package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ParamController {
    @GetMapping("/param")
    public String getParam(
            @RequestParam(required = true, name = "t") String title,
            @RequestParam(required = false, defaultValue = "未設定", name = "m") String message,
            Model model
    ){
        model.addAttribute("title", title);
        model.addAttribute("message", message);
        return "param";
    }
}
