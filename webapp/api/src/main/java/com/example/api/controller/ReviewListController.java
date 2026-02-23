package com.example.api.controller;

import com.example.api.entity.Review;
import com.example.api.form.ReviewSearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReviewListController {
    @PostMapping("/search-review")
    private String serachReview(
            @ModelAttribute ReviewSearchForm from,
            Model model
            ){
        List<Review> list = new ArrayList<Review>();
        // テストデータ
        // 1件目
        Review review = new Review();
        review.setReviewId(1);
        review.setRestaurantId(1);
        review.setUserId("user1");
        review.setVisitDate(LocalDate.of(2027,7,1));
        review.setRating(3);
        review.setComment("comment1");
        list.add(review);
        // 2件目
        review = new Review();
        review.setReviewId(2);
        review.setRestaurantId(1);
        review.setUserId("user2");
        review.setVisitDate(LocalDate.of(2027,7,2));
        review.setRating(4);
        review.setComment("comment2");
        list.add(review);

        if(list.size() > 0){
            model.addAttribute("reviewList", list);
        }

        return "review-list";
    }
}
