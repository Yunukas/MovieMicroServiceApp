package com.yy.app.entity;

import java.util.ArrayList;
import java.util.List;

public class UserRating {

    private String user;
    private List<MovieRating> movieRatings;


    public UserRating(String user) {
        this.user = user;
        this.movieRatings = new ArrayList<>();
    }

    public UserRating() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<MovieRating> getMovieRatings() {
        return movieRatings;
    }

    public void addMovieRating(MovieRating movieRating) {
        this.movieRatings.add(movieRating);
    }
}
