package com.example.api.controller;

import com.example.api.form.ReviewRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {
    @GetMapping("/show-review-form")
    public String showReviewForm(@ModelAttribute ReviewRegisterForm form){
        return "register-review";
    }

    @PostMapping("/register-review-ret")
    public String registerReviewRet(@ModelAttribute ReviewRegisterForm form){
        System.out.println(form);
        return "register-review";
    }

    @PostMapping("/register-review")
    public String registerReview(@ModelAttribute ReviewRegisterForm form){
        System.out.println(form);
        return "confirm-register-review";
    }

    @PostMapping("/confirm-register-review")
    public String confirmRegisterReview(ReviewRegisterForm form, Model model){
        model.addAttribute("msg", "レビュー登録完了しました");
        System.out.println(form + ":登録完了");
        return "complete-register-review";
    }


    }
