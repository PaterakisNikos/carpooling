package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by Nikos on 4/18/2019.
 */
public class Driver extends User {
	
	public Driver(String username, String password) {
		this(username, password, null, null, null);
	}

	public Driver(String username, String password, String firstname, String lastname, CreditCard[] cards) {
		super(username, password, firstname, lastname, cards);
	}
	
	public Ride createRide(LatLng start_location, Date start_time, LatLng end_location, double price, int number_of_passengers) {
		return new Ride(new RideInfo(true, start_location, start_time), new RideInfo(false, end_location, null), price, this.getUsername(), number_of_passengers);
	}
	
}
