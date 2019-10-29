package com.yy.app.controller;

import com.yy.app.entity.Movie;
import com.yy.app.entity.Rating;
import com.yy.app.entity.User;
import com.yy.app.entity.UserRating;
import com.yy.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppController {

    @Autowired
    private AppService appService;

    @PostMapping("/movie")
    public void addMovie(@RequestBody Movie movie){
        appService.addMovie(movie);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user){
        appService.addUser(user);
    }

    @PostMapping("/rating")
    public void addRating(@RequestBody Rating rating){
        appService.addRating(rating);
    }

    @GetMapping("/getUserRatings/{userId}")
    public UserRating getRatings(@PathVariable("userId") Long userId){
        return appService.getRatingsWithUserId(userId);
    }
}
