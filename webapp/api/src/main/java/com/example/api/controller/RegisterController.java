package com.example.api.controller;

import com.example.api.entity.Review;
import com.example.api.form.ReviewRegisterForm;
import com.example.api.form.SignUpForm;
import com.example.api.service.RegisterService;
import com.example.api.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService service;

    private final SignUpService signUpService;
    @PostMapping("/register")
    public String registerNewUser(@Validated @RequestBody SignUpForm signUpForm) {
        System.out.println(signUpForm.getLoginId());
        System.out.println(signUpForm.getPassword());
        return signUpService.SignUp();
    }





    @PostMapping("/show-review-form")
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
