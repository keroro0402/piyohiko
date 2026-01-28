package com.example.api.controller;

import com.example.api.entity.Review;
import com.example.api.form.ReviewRegisterForm;
import com.example.api.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService service;

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
    public String registerReview(
            @Validated @ModelAttribute ReviewRegisterForm form,
            BindingResult result
    ){
        if(result.hasErrors()){
            return "register-review";
        }
        System.out.println(form);
        return "confirm-register-review";
    }

    @PostMapping("/confirm-register-review")
    public String confirmRegisterReview(
            @Validated ReviewRegisterForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes,
            Model model
    ){
        if(result.hasErrors()){
            return "register-review";
        }

        Review r = new Review();
        r.setRestaurantId(form.getRestaurantId());
        r.setUserId(form.getUserId());
        r.setVisitDate(form.getVisitDate());
        r.setRating(form.getRating());
        r.setComment(form.getComment());
        service.register(r);

        /*フラッシュスコープにmsg登録*/
        redirectAttributes.addFlashAttribute("msg", "aji_dbにレビュー登録完了");
        return "redirect:/complete";
    }
}
