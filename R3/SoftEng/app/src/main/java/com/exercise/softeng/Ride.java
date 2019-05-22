package com.exercise.softeng;



/**
 * Created by Vasilis on 4/18/2019.
 */

public class Ride {
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

    public Request[] getRequests() {
        return requests;
    }

    private Request[] requests;
    private int current_requests;

    public Ride(RideInfo start, RideInfo end, double price, String driver_username, int how_many_passengers){
        if(how_many_passengers<=0)return;
        if(!(User.usernames.contains(driver_username)))return;
        if(price<=0.0)return;
        this.start=start;
        this.end=end;
        this.price=price;
        driver=driver_username;
        requests=new Request[how_many_passengers];
        current_requests=0;
    }

    public boolean creation_successfull(){return driver!=null;}

    public boolean add_request(Request req){
        if (current_requests!=requests.length){
            requests[current_requests]=req;
            current_requests++;
            return true;
        }
        return false;
    }

    public boolean remove_request(String username){
        for(int i=0;i<current_requests;i++){
            if(requests[i].getPassenger().equals(username)){
                for (int j=i;j<requests.length-1;j++) requests[j]=requests[j+1];
                current_requests--;
                return true;
            }
        }
        return false;
    }

    public int get_aliveRequests(){return current_requests;}
}
