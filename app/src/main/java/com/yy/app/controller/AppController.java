package com.yy.app.controller;

import com.yy.app.config.AppConfig;
import com.yy.app.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AppController {

    final static Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    AppConfig appConfig;

    private String USER_URI;
    private String RATING_POST_URI;
    private String RATING_USER_URI;
    private String MOVIE_URI;

    @PostConstruct
    public void init(){
        USER_URI = "http://userservice/user/";
        RATING_POST_URI = "http://ratingservice/rating/";
        RATING_USER_URI = "http://ratingservice/rating/user/";
        MOVIE_URI = "http://movieservice/movie/";
    }

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/movie")
    public void addMovie(@RequestBody Movie movie){
        HttpEntity<Movie> movieHttpEntity = new HttpEntity<>(movie);
        restTemplate.postForObject(MOVIE_URI, movieHttpEntity, Movie.class);
    }

    @PostMapping("/user")
    public void addUser(@RequestBody User user){
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);
        restTemplate.postForObject(USER_URI, userHttpEntity, User.class);
    }

    @PostMapping("/rating")
    public void addRating(@RequestBody Rating rating){
        HttpEntity<Rating> ratingHttpEntity = new HttpEntity<>(rating);
        restTemplate.postForObject(RATING_POST_URI, ratingHttpEntity, Rating.class);
    }


    @GetMapping("/getUserRatings/{userId}")
    public UserRating getRatings(@PathVariable("userId") Long userId){

        UserRatings userRatings = restTemplate.getForObject(
                 RATING_USER_URI + userId
                , UserRatings.class );

        if(userRatings == null)
            return null;

        User user = restTemplate.getForObject(
                USER_URI + userId, User.class);

        UserRating userRating = new UserRating(user.getName());

        List<Rating> userRatingList = userRatings.getUserRatings();

        for(Rating rating : userRatingList){
//            logger.info(rating.getMovieId() + " " +
//                     rating.getRating() + " "
//                    + rating.getId() + " "  + rating.getUserId());

            Movie movie = restTemplate.getForObject(
                    MOVIE_URI + rating.getMovieId(), Movie.class);

//            logger.info(movie.getName() + " " +
//                    movie.getDesc() + " "
//                    + rating.getRating());

            userRating.addMovieRating(
                    new MovieRating(
                            movie.getName(),
                            movie.getDesc(),
                            rating.getRating()));
        }
        return userRating;
    }
}
