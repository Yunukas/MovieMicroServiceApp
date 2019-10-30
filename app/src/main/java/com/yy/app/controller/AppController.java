package com.yy.app.controller;

import com.yy.app.entity.Movie;
import com.yy.app.entity.Rating;
import com.yy.app.entity.User;
import com.yy.app.entity.UserRating;
import com.yy.app.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    private AppService appService;

    @GetMapping("/getUserRatings/{userId}")
    public UserRating getRatings(@PathVariable("userId") Long userId){
        return appService.getRatingsWithUserId(userId);
    }
}
