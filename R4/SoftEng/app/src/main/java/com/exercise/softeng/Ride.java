package com.exercise.softeng;


import java.util.ArrayList;

/**
 * Created by Vasilis on 4/18/2019.
 */

public class Ride {

    private static  int next_id=0;
    private RideInfo start;
    private RideInfo end;
    public double price;
    private String driver;//Username of the driver

    public RideInfo getStart() {
        return start;
    }

    public RideInfo getEnd() {
        return end;
    }

    public String getDriver() {
        return driver;
    }

    private int capasity;

    public final int ID;

    public Request[] getRequests() {
        Request[] data=new Request[requests.size()];
        int i=0;
        for(Request req: requests){
            data[i]=req;
            i++;
        }
        return data;
    }

    private ArrayList<Request> requests;
    private int current_requests;

    public Ride(RideInfo start, RideInfo end, double price, String driver_username, int how_many_passengers){
        if(how_many_passengers<=0){
            ID=-1;
            return;
        }
        if(!(User.getUsernames().contains(driver_username))){
            ID=-1;
            return;
        }
        if(price<=0.0){
            ID=-1;
            return;
        }
        this.start=start;
        this.end=end;
        this.price=price;
        driver=driver_username;
        requests=new ArrayList<Request>();
        current_requests=0;
        capasity=how_many_passengers;
        ID=next_id;
        next_id++;
    }

    public boolean creation_successfull(){return driver!=null;}

    public boolean add_request(Request req){
        if (current_requests!=capasity){
            requests.add(req);
            return true;
        }
        return false;
    }


    public void proccesRequest(int req_index, boolean accept){
        Request req= requests.get(req_index);
        if(accept){
            req.setState(Request.ACCEPTED_REQUEST);
            current_requests++;
        }
        else
            req.setState(Request.DECLINED_REQUEST);
    }







}
