package com.example.api.sandbox.controller;

import com.example.api.sandbox.form.ValidatedForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ValidatedController {
    @GetMapping("/validated")
    public String getValidated(@ModelAttribute ValidatedForm form){
        return "validated-form";
    }

    @PostMapping("/comfirm")
    public String showComfirm(
            @ModelAttribute @Validated ValidatedForm form,
            BindingResult result
            ){

        if(result.hasErrors()){
            return "validated-form";
        }

        return "comfirm";
    }
}
