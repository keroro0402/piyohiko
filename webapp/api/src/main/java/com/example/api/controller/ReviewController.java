package com.example.api.controller;

import com.example.api.form.ReviewRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReviewController {
    @GetMapping("/show-review-form")
    public String showReviewForm(){
        return "register-review";
    }


    @PostMapping("/register-review")
    public String registerReview(@ModelAttribute ReviewRegisterForm form){
        System.out.println(form);
        return "confirm-register-review";
    }

}
