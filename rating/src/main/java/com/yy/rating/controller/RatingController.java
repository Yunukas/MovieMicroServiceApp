package com.yy.rating.controller;

import com.yy.rating.entity.Rating;
import com.yy.rating.entity.UserRatings;
import com.yy.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingRepository ratingRepository;

    @PostMapping
    public void addRating(@RequestBody Rating rating){
        ratingRepository.save(rating);
    }

    @GetMapping("/user/{userId}")
    public UserRatings getUserRating(@PathVariable("userId") Long userId){
        final Optional<List<Rating>> optRating = ratingRepository.findByUserId(userId);

        if(optRating.isPresent()){
            List<Rating> res = optRating.get();
            UserRatings usr = new UserRatings();
            usr.setUserRatings(res);
            return usr;
        }
        return null;
    }
}
