package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* HTMLを返す場合のアノテーション */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }
}
