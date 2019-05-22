package com.exercise.softeng;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vasilis on 4/19/2019.
 */
public class CreditCardTest {

    @Test
    public void testCC_uniqueNumbers(){
        String input_number="2";
        String input_firstname="kitsos";
        String input_lastname="tou thoma";
        int input_cvc=6877;
        CreditCard cc=new CreditCard(input_firstname, input_lastname, input_number, input_cvc);
        assertTrue("First card must always be successfull",cc.creation_successfull());
        cc=new CreditCard("nick", "pater", input_number, input_cvc);
        assertFalse("All cards must have unique number", cc.creation_successfull());
    }

    @Test
    public void testCC_ConstructorSuccess(){
        String input_firstname="bill";
        String input_lastname="Morm";
        String input_number="4";
        int input_cvc=6259;
        CreditCard cc=new CreditCard(input_firstname, input_lastname, input_number, input_cvc);
        assertEquals("When credit card constructor done input_firstname must match cc.firstname", input_firstname,cc.firstname);
        assertEquals("When credit card constructor done input_lastname must match cc.lastname", input_lastname,cc.lastname);
        assertEquals("When credit card constructor done input_number must match cc.number", input_number,cc.number);
        assertEquals("When credit card constructor done input_cvc must match cc.cvc", input_cvc,cc.cvc);
    }
}