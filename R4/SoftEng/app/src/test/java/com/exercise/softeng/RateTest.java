package com.exercise.softeng;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vasilis on 4/20/2019.
 */
public class RateTest {


    @Test
    public void testRateSuccessfullCreation(){
        Driver driver=new Driver("ElpsyCongroo","pass");
        Passenger passenger=new Passenger("nick_pater", "pass");
        Rate rate=new Rate(passenger.getUsername(),driver.getUsername(), 5.0);
        assertTrue("Creation of rate must be succesfull when different usernames of Users are used and rating>=0.0",rate.creation_successfull());
        assertEquals("Rater should be equals passenger.getUsername()", passenger.getUsername(), rate.getWho_makes_the_rate());
        assertEquals("Rated should be equals driver.getUsername()", driver.getUsername(), rate.getWho_is_rating());
        assertTrue("rating should be equals 5.0", 5.0==rate.getRating());//Something with delta when using floating point numbers
    }

    @Test
    public void testRateFailedCreation(){
        Driver driver=new Driver("ElpsyCongroo","pass");
        Passenger passenger=new Passenger("nick_pater", "pass");
        Rate rate=new Rate(driver.getUsername(), driver.getUsername(), 4.0);
        assertFalse("Creation of rating where rater and rated is the same should fail", rate.creation_successfull());
        rate=new Rate("test", passenger.getUsername(), 4.2);
        assertFalse("Creation with username that is not match a username of User object should fail", rate.creation_successfull());
        rate=new Rate(passenger.getUsername(),"test", 4.2);
        assertFalse("Creation with username that is not match a username of User object should fail", rate.creation_successfull());
        rate=new Rate(passenger.getUsername(), passenger.getUsername(), 5.0);
        assertFalse("Creation of rate with rater and rated is the same should fail", rate.creation_successfull());
        rate=new Rate(passenger.getUsername(),driver.getUsername(), -1.0);
        assertFalse("Creation of rate with negative rating value should fail", rate.creation_successfull());
    }
}