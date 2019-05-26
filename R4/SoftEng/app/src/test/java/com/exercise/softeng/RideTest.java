package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by Vasilis on 4/19/2019.
 */
public class RideTest {

    /*Arguments that will quarante a successfull creation of Ride*/
    Driver input_driver=new Driver("ElpsyCongroo", "pass", "Bill", "looo", null);
    RideInfo input_start_info=new RideInfo(true, new LatLng(38.049186, 23.835265), new Date(System.currentTimeMillis()));
    RideInfo input_end_info=new RideInfo(false, new LatLng(37.994053, 23.732459), null);
    double input_price=120.0;
    int input_number_of_passengers=3;


    @Test
    public void testRideCreationSuccess(){
        Ride ride=new Ride(input_start_info, input_end_info, input_price, input_driver.getUsername(), input_number_of_passengers);
        assertTrue("Creation of a Ride with all arguments being correct", ride.creation_successfull());
    }

    @Test
    public void testRideCreationFailures(){
        Ride ride=new Ride(input_start_info, input_end_info, input_price, "kitsos", input_number_of_passengers);
        assertFalse("Creation with username that not exist should fail", ride.creation_successfull());
        ride=new Ride(input_start_info, input_end_info, 0, input_driver.getUsername(), input_number_of_passengers);
        assertFalse("Creation with price at 0 or less should fail", ride.creation_successfull());
        ride=new Ride(input_start_info, input_end_info, input_price, input_driver.getUsername(), 0);
        assertFalse("Number of passengers upon creation must be >0", ride.creation_successfull());
    }


    @Test
    public void testAddRequest(){
        CreditCard[] driver_cards={new CreditCard("nick", "pater", "3", 678)};
        CreditCard[] passenger_cards={new CreditCard("totos", "totou", "4", 876)};
        Driver driver=new Driver("PaterNick", "pass", "nick", "pater", driver_cards);
        Passenger passenger=new Passenger("Totos", "pass", "totos", "totou", passenger_cards);
        Ride ride=new Ride(input_start_info, input_end_info, input_price, driver.getUsername(), input_number_of_passengers);
        Request req=new Request(new LatLng(38.049186, 23.835265),passenger.getUsername());
        ride.add_request(req);
        //assertEquals("After an successfull add_request() to the ride the ride.aliveRequests() must be 1", 1,ride.get_aliveRequests());
        for(int i=0;i<ride.getRequests().length;i++){
            if(ride.getRequests()[i]==null)break;
            if(ride.getRequests()[i].equals(req))return;
        }
        fail("The add it request is not on the requests");
    }

    @Test
    public void testRemoveRequest(){
        CreditCard[] driver_cards={new CreditCard("nick", "pater", "3", 678)};
        CreditCard[] passenger_cards={new CreditCard("totos", "totou", "4", 876)};
        Driver driver=new Driver("PaterNick", "pass", "nick", "pater", driver_cards);
        Passenger passenger=new Passenger("Totos", "pass", "totos", "totou", passenger_cards);
        Ride ride=new Ride(input_start_info, input_end_info, input_price, driver.getUsername(), input_number_of_passengers);
        Request req=new Request(new LatLng(38.049186, 23.835265),passenger.getUsername());
        ride.add_request(req);
        //ride.remove_request(passenger.getUsername());
        //assertEquals("After an successfull remove_request() to the ride the ride.aliveRequests() must be 0", 0,ride.get_aliveRequests());
        //for(int i=0;i<ride.getRequests().length;i++){
        //    if(ride.getRequests()[i]==null) break;
        //    if(ride.getRequests()[i].equals(req)) fail("After a successfull remove_request() the ride request must not contains the req");
       // }
    }

}