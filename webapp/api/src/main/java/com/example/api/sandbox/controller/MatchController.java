package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.MatchDto;
import com.example.api.sandbox.form.MatchForm;
import com.example.api.sandbox.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class MatchController {

    private final MatchService matchService;

    @GetMapping("/show-match-form")
    public String showForm(
            @ModelAttribute
            MatchForm form
    ){
        return "match-form";
    }

    @PostMapping("/match-complete")
    public String matchComplete(
            @ModelAttribute
            @Validated
            MatchForm form,
            BindingResult result,
            Model model
    ){
        if(result.hasErrors()){
            return "match-form";
        }

        MatchDto dto = new MatchDto();
        dto.setDate(form.getDate());
        dto.setPlace(form.getPlace());
        dto.setMaxPlayers(form.getMaxPlayers());

        try{
            matchService.register(dto);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "match-form";
        }

        return "match-complete";
    }
}
