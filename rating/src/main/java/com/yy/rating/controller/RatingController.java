package com.yy.rating.controller;

import com.yy.rating.entity.Rating;
import com.yy.rating.entity.UserRatings;
import com.yy.rating.repository.RatingRepository;
import com.yy.rating.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @PostMapping
    public void addRating(@RequestBody Rating rating){
        ratingService.addRating(rating);
    }

    @GetMapping("/user/{userId}")
    public UserRatings getUserRating(@PathVariable("userId") Long userId){
        return ratingService.getUserRatingWithId(userId);
    }
}
