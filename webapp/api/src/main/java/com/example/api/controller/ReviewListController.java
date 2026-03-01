package com.example.api.controller;

import com.example.api.entity.Review;
import com.example.api.form.ReviewSearchForm;
import com.example.api.service.ReviewListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewListController {

    private final ReviewListService service;

    @PostMapping("/search-review")
    private String serachReview(
            @ModelAttribute ReviewSearchForm from,
            Model model
            ){

        List<Review> list = service.findByRestaurantId(from.getRestaurantId());

        if(list.size() > 0){
            model.addAttribute("reviewList", list);
        }

        return "review-list";
    }
}
