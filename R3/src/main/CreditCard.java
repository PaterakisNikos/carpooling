package com.exercise.softeng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikos on 4/18/2019.
 */
public class CreditCard {
	public String firstname;
	public String lastname;
	public String number;
	public int cvc;

	/*
	 * 	Contains all the numbers that are already in use
	 * 		------Each CreditCard has a unique number-------
	 **/
	public static List<String> numbers=new ArrayList<String>();


	public CreditCard(String f_name, String l_name, String number, int cvc) {
		if(numbers.contains(number))return;
		firstname=f_name;
		lastname=l_name;
		this.number=number;
		numbers.add(this.number);
		this.cvc=cvc;
	}

	public boolean creation_successfull(){return number!=null;}
	
}
