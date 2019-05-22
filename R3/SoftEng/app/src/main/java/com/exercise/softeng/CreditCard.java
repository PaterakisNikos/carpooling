package com.exercise.softeng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikos on 4/18/2019.
 */
public class CreditCard {
	private String firstname;
	private String lastname;
	private String number;
	private int cvc;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}

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
