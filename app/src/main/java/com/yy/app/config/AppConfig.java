package com.yy.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${USER_URI}")
    private String userUri;

    @Value("${RATING_POST_URI}")
    private String ratingPostUri;

    @Value("${RATING_USER_URI}")
    private String ratingUserUri;

    @Value("${MOVIE_URI}")
    private String movieUri;

    public String getUserUri() {
        return userUri;
    }

    public String getRatingPostUri() {
        return ratingPostUri;
    }

    public String getRatingUserUri() {
        return ratingUserUri;
    }

    public String getMovieUri() {
        return movieUri;
    }


    //    RATING_POST_URI: "http://localhost:8082/rating/";
//    RATING_USER_URI: "http://localhost:8082/rating/user/";
//    MOVIE_URI:
}
