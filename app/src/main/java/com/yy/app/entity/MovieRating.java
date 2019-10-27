package com.yy.app.entity;

public class MovieRating {
    private String movieName;
    private String desc;
    private int rating;

    public MovieRating(String movieName, String desc, int rating) {
        this.desc = desc;
        this.movieName = movieName;
        this.rating = rating;
    }

    public String getMovieName() {
        return movieName;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
