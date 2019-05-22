package com.exercise.softeng;

/**
 * Created by Vasilis on 4/18/2019.
 */

public class Rate {

    public String who_makes_the_rate;
    public String who_is_rating;

    public double rating;

    public Rate(String rater, String rated, double rating){
        if(!(User.usernames.contains(rater))||!(User.usernames.contains(rated))||rated.equals(rater)||rating<0.0)return;
        who_makes_the_rate=rater;
        who_is_rating=rated;
        this.rating=rating;
    }

    public boolean creation_successfull(){return who_makes_the_rate!=null;}

}
