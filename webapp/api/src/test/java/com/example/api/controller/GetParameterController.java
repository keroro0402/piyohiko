package com.example.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GetParameterController {
    @GetMapping("/getparameter")
    public String getParameter(
            /*
            * デフォルト値の設定：defaultValue
            * URLパラメータを別名で取得：name
            * */
            @RequestParam(defaultValue = "指定されたタイトル用パラメータはありません", name = "t") String title,
            @RequestParam(defaultValue = "指定されたサブタイトル用パラメータはありません", name="st") String subTitle,
            Model model
    ){
        /*
        * view　に反映するために　model　に変数を設定
        * */
        model.addAttribute("templateTitle", title);
        model.addAttribute("templateSubTitle", subTitle);
        return "get-parameter";
    }
}
