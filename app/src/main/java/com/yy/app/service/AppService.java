package com.yy.app.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yy.app.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AppService {

    @Autowired
    private RestTemplate restTemplate;

    final static Logger logger = LoggerFactory.getLogger(AppService.class);

    private String USER_URI = "http://userservice/user/";
    private String RATING_POST_URI = "http://ratingservice/rating/";
    private String RATING_USER_URI = "http://ratingservice/rating/user/";
    private String MOVIE_URI = "http://movieservice/movie/";

    @HystrixCommand
    public void addMovie(Movie movie){
        HttpEntity<Movie> movieHttpEntity = new HttpEntity<>(movie);
        restTemplate.postForObject(MOVIE_URI, movieHttpEntity, Movie.class);
    }

    @HystrixCommand
    public void addUser(User user){
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user);
        restTemplate.postForObject(USER_URI, userHttpEntity, User.class);
    }

    @HystrixCommand
    public void addRating(Rating rating){
        HttpEntity<Rating> ratingHttpEntity = new HttpEntity<>(rating);
        restTemplate.postForObject(RATING_POST_URI, ratingHttpEntity, Rating.class);
    }

    @HystrixCommand(fallbackMethod = "ratingFallBack",
            threadPoolKey = "userRatingsThreadPool",
            threadPoolProperties = {
                @HystrixProperty(name = "coreSize", value = "20"),
                @HystrixProperty(name = "maxQueueSize", value = "10")
    }, commandProperties = {
            // amount of consecutive calls that must occur withing a 10-second window
            // before Hystrix will consider tripping the circuit breaker for the call
            @HystrixProperty(
                    name = "circuitBreaker.requestVolumeThreshold",
                    value = "10"),
            // Percentage of calls that must fail after requestVolumeThreshold value
            // has been passed before the circuit breaker is tripped
            @HystrixProperty(
                    name = "circuitBreaker.errorThresholdPercentage",
                    value = "75"),
            // Amount of time Hystrix will sleep once the circuit breaker is tripped
            // before Hystrix will allow another call through
            // to see if the service is healthy again
            @HystrixProperty(
                    name = "circuitBreaker.sleepWindowInMilliseconds",
                    value = "7000"),
            // Size of window that will be used by Hystrix to monitor
            // for problems with a service calls ( default value is 10000ms)
            @HystrixProperty(
                    name = "metrics.rollingStats.timeInMilliseconds",
                    value = "15000"),
            // Number of times statistics are collected in the window defined
            // Hystrix collects metrics in buckets during this window and checks the stats in
            // those buckets to determine if the remote resource call is failing
            // The number of buckets define must evenly divide into the overall
            // number of milliseconds set for rollingStatus.inMilliSeconds stats
            // In this setting, Hystrix will use a 15 second window and collect
            // statistics data into five buckets of three seconds in length
            @HystrixProperty(
                    name = "metrics.rollingStats.numBuckets",
                    value = "5")
        }
    )
    public UserRating getRatingsWithUserId(Long userId){

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
            Movie movie = restTemplate.getForObject(
                    MOVIE_URI + rating.getMovieId(), Movie.class);

            userRating.addMovieRating(
                    new MovieRating(
                            movie.getName(),
                            movie.getDesc(),
                            rating.getRating()));
        }
        return userRating;
    }

    private UserRating ratingFallBack(Long userId){
        return new UserRating("Unknown");
    }
}
