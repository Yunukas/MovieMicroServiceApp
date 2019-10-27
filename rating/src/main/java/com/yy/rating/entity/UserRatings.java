package com.yy.rating.entity;

import java.util.ArrayList;
import java.util.List;

public class UserRatings {
    private List<Rating> userRatings = new ArrayList<>();

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }
}
