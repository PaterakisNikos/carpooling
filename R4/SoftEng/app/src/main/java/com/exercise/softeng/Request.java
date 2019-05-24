package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vasilis on 4/18/2019.
 */

public class Request{

    public static final int PENDING_REQUEST=0;
    public static final int ACCEPTED_REQUEST=1;
    public static final int DECLINED_REQUEST=2;

    private static long next_id=1;
    public long request_id;
    public LatLng position;

    public String getPassenger() {
        return passenger;
    }

    private String passenger;//Username of the passenger
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Request(LatLng position, String passenger_username){
        if(!User.getUsernames().contains(passenger_username)||position==null)return;
        request_id=next_id;
        next_id++;
        this.position=position;
        passenger=passenger_username;
        state=PENDING_REQUEST;
    }

    boolean creation_successfull(){return passenger!=null;}
}
