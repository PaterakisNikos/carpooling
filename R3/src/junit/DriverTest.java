package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class DriverTest {

    @Test
    public void driverTest() {
        String username="Bill";
        String password="password";
        String firstname="Bill";
        String lastname="Mars";
        CreditCard cc=null;




        Driver driver=new Driver(username, password);
        assertTrue("Driver created successfully.", driver.creation_successfull());

        Ride ride=driver.createRide(new LatLng(38.049186, 23.835265), new Date(System.currentTimeMillis()), new LatLng(37.994053, 23.732459), 5.90, 4);
        assertTrue("Ride created successfully.", ride.creation_successfull());

        ride=driver.createRide(new LatLng(38.049186, 23.835265), new Date(System.currentTimeMillis()), new LatLng(38.049186, 23.835265), 5.90, 4);
        assertFalse("Start and finish positions must be different.", ride.creation_successfull());


    }

}