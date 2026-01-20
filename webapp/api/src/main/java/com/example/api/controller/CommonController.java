package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
    @GetMapping("/complete-register-topic")
    public String complete(Model model){
        model.addAttribute("title", "トピック登録完了");
        model.addAttribute("messageComplete", "登録完了しました");
        model.addAttribute("messageToRegister", "入力ページへ");
        return "complete-register-topic";
    }
}
