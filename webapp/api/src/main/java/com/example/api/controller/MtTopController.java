package com.example.api.controller;

import com.example.api.entity.MtTop;
import com.example.api.form.MtTopForm;
import com.example.api.service.MtTopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class MtTopController {

    private final MtTopService mtTopService;

    @GetMapping("/mt-top")
    public String showMtForm(@ModelAttribute MtTopForm form){
        return "mt-form";
    }

    @PostMapping("/mt-top")
    public String showMtFormRe(@ModelAttribute MtTopForm form){
        return "mt-form";
    }

    @PostMapping("/register-mttop")
    public String showConfirmMtForm(@Validated MtTopForm form, BindingResult result){
        if(result.hasErrors()){
            return "mt-form";
        }
        return "mt-confirm";
    }

    @PostMapping("/confirm-register-mttop")
    public String showComplte(
            @Validated MtTopForm form,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ){
        if(result.hasErrors()){
            return "mt-form";
        }

        MtTop t = new MtTop();
        t.setName(form.getName());
        t.setCatchPhrase(form.getCatchPhrase());
        mtTopService.register(t);

        redirectAttributes.addFlashAttribute("msg", "登録完了");
        return "redirect:/mt-complete";
    }
}
