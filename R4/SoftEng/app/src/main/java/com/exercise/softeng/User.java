package com.exercise.softeng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasilis on 4/18/2019.
 */
public class User {

	public String first_name;
	public String last_name;
	private String username;
	private String password;


	public static List<String> getUsernames() {
		return usernames;
	}

	/*
         * 	Contains all the usernames that are already in use
         * 		------Each user has a unique username-------
         **/
	private static List<String>usernames=new ArrayList<String>();

	public ArrayList<CreditCard> getCards() {
		return cards;
	}

	private ArrayList<CreditCard> cards=new ArrayList<CreditCard>();
	
	public User(String username, String password) {
		this(username, password, null, null, null);
	}
	
	
	public User(String username, String password, String first_name, String last_name, CreditCard[] cards) {
		if(usernames.contains(username)) {
			this.cards=null;
		}
		else {
			usernames.add(username);
			this.username=username;
			this.password=password;
			this.first_name=first_name;
			this.last_name=last_name;
			if(cards!=null){
				for (int i=0;i<cards.length;i++){
					this.cards.add(cards[i]);
				}
			}
		}
	}
	
	
	
	public String getUsername() {return this.username;}
	
	public String getPassword() {return this.password;}
	
	public boolean creation_successfull() {return username!=null;}

	public Rate create_rating(String rated_username, double rating){ return new Rate(this.getUsername(), rated_username, rating);}

}
