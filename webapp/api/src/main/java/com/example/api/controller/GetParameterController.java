package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GetParameterController {
    @GetMapping("/getparameter")
    public String getParameter(
            @RequestParam(defaultValue = "指定されたTitlte用パラメータはありません", name = "t") String title,
            @RequestParam(defaultValue = "指定されたサブタイトルはありません", name="st") String subTitle,
            Model model
    ){
        model.addAttribute("templateTitle", title);
        model.addAttribute("templateSubTitle", subTitle);
        return "get-parameter";
    }
}
