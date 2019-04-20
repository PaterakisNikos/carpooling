package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vasilis on 4/18/2019.
 */

public class Request {

    public static long next_id=1;
    public long request_id;
    public LatLng position;
    public String passenger;//Username of the passenger

    public Request(LatLng position, String passenger_username){
        if(!User.usernames.contains(passenger_username)||position==null)return;
        request_id=next_id;
        next_id++;
        this.position=position;
        passenger=passenger_username;
    }

    boolean creation_successfull(){return passenger!=null;}
}
