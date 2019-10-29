package com.yy.rating.service;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yy.rating.entity.Rating;
import com.yy.rating.entity.UserRatings;
import com.yy.rating.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @HystrixCommand
    public UserRatings getUserRatingWithId(Long userId){
        final Optional<List<Rating>> optRating = ratingRepository.findByUserId(userId);

        if(optRating.isPresent()){
            List<Rating> res = optRating.get();
            UserRatings usr = new UserRatings();
            usr.setUserRatings(res);
            return usr;
        }
        return null;
    }
    @HystrixCommand
    public void addRating(Rating rating) {
        ratingRepository.save(rating);
    }
}
