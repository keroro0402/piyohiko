package com.example.api.sandbox.controller;

import com.example.api.sandbox.dto.TeamDto;
import com.example.api.sandbox.form.TeamForm;
import com.example.api.sandbox.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TeamController {
    private final TeamService teamService;
    @GetMapping("/show-team-form")
    public String showForm(@ModelAttribute TeamForm form){
        return "team-form";
    }

    @PostMapping("/register")
    public String complete(
            @ModelAttribute
            @Validated
            TeamForm form,
            BindingResult result){
        if(result.hasErrors()){
            return "team-form";
        }


        TeamDto dto = new TeamDto();
        dto.setName(form.getName());
        System.out.println(dto);
        teamService.register(dto);

        return "/team-complete";
    }

}
