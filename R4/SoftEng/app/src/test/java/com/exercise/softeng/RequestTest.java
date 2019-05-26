package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vasilis on 4/20/2019.
 */
public class RequestTest {

    @Test
    public void testRequestConstructorSuccess(){
        Passenger input_passenger=new Passenger("ElpsyCongroo","pass");
        LatLng input_position=new  LatLng(38.049186, 23.835265);
        Request req=new Request(input_position, input_passenger.getUsername());
        assertTrue("Upon successfull creation of request req.creation_successfull must return true", req.creation_successfull());
        assertEquals("After successfull creation of request, req.posiotion should match input_position", input_position, req.position);
        assertEquals("After successfull creation of request, req.passenger should match input_passenger.getUsername()", input_passenger.getUsername(), req.getPassenger());

    }

    @Test
    public void testRequestConstructorFail(){
        Passenger input_passenger=new Passenger("ElpsyCongroo","pass");
        LatLng input_position=new  LatLng(38.049186, 23.835265);
        Request req=new Request(input_position, "kitsos");
        assertFalse("Username that is not used by a user should not create a request", req.creation_successfull());
        req=new Request(null, input_passenger.getUsername());
        assertFalse("null position should not create request", req.creation_successfull());
    }
}