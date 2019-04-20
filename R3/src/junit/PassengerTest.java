package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class PassengerTest {


    @Test
    public void testPassenger() {

        String username="Bill";
        String password="password";


        Driver input_driver=new Driver("ElpsyCongroo", "pass", "Bill", "looo", null);
        RideInfo input_start_info=new RideInfo(true, new LatLng(38.049186, 23.835265), new Date(System.currentTimeMillis()));
        RideInfo input_end_info=new RideInfo(false, new LatLng(37.994053, 23.732459), null);
        double input_price=120.0;
        int input_number_of_passengers=3;

        Passenger passenger=new Passenger(username, password);
        assertTrue("Passenger created successfully.", passenger.creation_successfull());

        Ride ride=new Ride(input_start_info, input_end_info, input_price,  input_driver.getUsername(), input_number_of_passengers);
        Request request=passenger.create_request(new LatLng(37.994053, 23.732459), ride);

        assertTrue("Request created successfully.", request.creation_successfull());
    }

}