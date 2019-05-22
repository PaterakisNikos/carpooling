package com.exercise.softeng;

/**
 * Created by Vasilis on 4/18/2019.
 */

public class Rate {

    private String who_makes_the_rate;
    private String who_is_rating;

    public String getWho_makes_the_rate() {
        return who_makes_the_rate;
    }

    public void setWho_makes_the_rate(String who_makes_the_rate) {
        this.who_makes_the_rate = who_makes_the_rate;
    }

    public String getWho_is_rating() {
        return who_is_rating;
    }

    public void setWho_is_rating(String who_is_rating) {
        this.who_is_rating = who_is_rating;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    private double rating;

    public Rate(String rater, String rated, double rating){
        if(!(User.usernames.contains(rater))||!(User.usernames.contains(rated))||rated.equals(rater)||rating<0.0)return;
        who_makes_the_rate=rater;
        who_is_rating=rated;
        this.rating=rating;
    }

    public boolean creation_successfull(){return who_makes_the_rate!=null;}

}
