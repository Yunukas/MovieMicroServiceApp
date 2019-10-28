package com.yy.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AppConfig {

    @Value("${user.uri}")
    private String userUri ="";

    @Value("${rating.post.uri}")
    private String ratingPostUri="";

    @Value("${rating.user.uri}")
    private String ratingUserUri="";

    @Value("${movie.uri}")
    private String movieUri="";

    public void setUserUri(String userUri) {
        this.userUri = userUri;
    }

    public void setRatingPostUri(String ratingPostUri) {
        this.ratingPostUri = ratingPostUri;
    }

    public void setRatingUserUri(String ratingUserUri) {
        this.ratingUserUri = ratingUserUri;
    }

    public void setMovieUri(String movieUri) {
        this.movieUri = movieUri;
    }

    public AppConfig() {
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

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
}
