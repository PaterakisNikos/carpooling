package com.exercise.softeng;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vasilis on 4/19/2019.
 */
public class UserTest {

    @Test
    public void TestUserNameUnique(){
        String input_username="bill";
        String input_password="pass1";
        User u1=new User(input_username, input_password);
        assertTrue("Creation of first user must be successfull",u1.creation_successfull());
        User u2=new User(input_username, input_password);
        assertFalse("Username must be unique",u2.creation_successfull());
    }

    @Test
    public void TestTwoArgsConstructor(){
        String input_username="bill";
        String input_password="pass";
        User user=new User(input_username, input_password);
        assertNull("When using constructor of 2 args first name must be null", user.first_name);
        assertNull("When using constructor of 2 args last name must be null", user.first_name);
        assertEquals("When using constructor of 2 args size of cards arraylist should be 0", 0, user.cards.size());
        assertEquals("When using constructor of 2 args getUsername should be returning the input_username", input_username, user.getUsername());
        assertEquals("When using constructor of 2 args getPassword should be returning the input_password", input_password, user.getPassword());
    }

    @Test
    public void TestFullArgsConstructor(){
        String input_username="bill";
        String input_password="pass";
        String input_firstname="Billys";
        String input_lastname="Mormo";
        CreditCard[] ccs={new CreditCard("nick","pater", "3", 9999),new CreditCard("billys", "mormo", "2", 2866)};
        User user=new User(input_username, input_password, input_firstname,input_lastname,ccs);
        assertEquals("When using constructor with all args user.getUsername() must match input_username", input_username, user.getUsername());
        assertEquals("When using constructor with all args user.getPassword() must match input_password", input_password, user.getPassword());
        assertEquals("When using constructor with all args user.first_name must match input_firstname", input_firstname, user.first_name);
        assertEquals("When using constructor with all args user.last_name must match input_lastname", input_lastname, user.last_name);
        //No assertArrayEquals for custom objects array so we hard code the checks
        int i=0;
        for (CreditCard cc : ccs) {
            assertEquals("When using constructor with all args ccs array must match with user.cards", cc, user.cards.get(i));
            i++;
        }
    }

    @Test
    public void testRateCreationSuccessfull(){
        Driver driver=new Driver("nick", "pass");
        Passenger passenger=new Passenger("bill", "pass");
        Rate rate=driver.create_rating(passenger.getUsername(), 5.0);
        assertTrue("Rate creation should be successfull", rate.creation_successfull());
        assertEquals("Rater should be equals driver.getUsername()", driver.getUsername(), rate.who_makes_the_rate);
        assertEquals("Rated should be equals passenger.getUsername()", passenger.getUsername(), rate.who_is_rating);
        assertTrue("rating should be equals 5.0", 5.0==rate.rating);//Something with delta when using floating point numbers
    }

    @Test
    public void testRateCreationFail(){
        Driver driver=new Driver("nick", "pass");
        Passenger passenger=new Passenger("bill", "pass");
        Rate rate=driver.create_rating(driver.getUsername(), 5.0);
        assertFalse("Creation of rate with same username as rated and rater should fail",rate.creation_successfull());
        rate=driver.create_rating("kitsos", 5.0);
        assertFalse("Creation of rate with username not in used usernames by user should fail",rate.creation_successfull());
        rate=driver.create_rating(passenger.getUsername(), -1.0);
        assertFalse("Creation of rate with negative rating should fail", rate.creation_successfull());
    }


}