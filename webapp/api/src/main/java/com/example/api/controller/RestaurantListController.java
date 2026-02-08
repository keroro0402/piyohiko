package com.example.api.controller;

import com.example.api.entity.Restaurant;
import com.example.api.form.RestaurantSearchForm;
import com.example.api.service.RestaurantListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class RestaurantListController {

    private final RestaurantListService service;

    @GetMapping("/top")
    private String restaurantList(
            @ModelAttribute RestaurantSearchForm form
    ){
        return "restaurant-list";
    }

    @PostMapping("/restaurant-search")
    private String restaurantSearch(
            @ModelAttribute RestaurantSearchForm form,
            Model model
    ){

        List<Restaurant> list = service.findByNameWildcard(form.getRestaurantName());

        model.addAttribute("restaurantList", list);

        return "restaurant-list";
    }

}
