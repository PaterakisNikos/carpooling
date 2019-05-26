package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Vasilis on 4/18/2019.
 */
public class Passenger extends User {

	public Passenger(String username, String password) {
		this(username, password, null, null, null);
	}
	
	public Passenger(String username, String password, String first_name, String last_name, CreditCard[] cards) {
		super(username, password, first_name, last_name, cards);
	}
	
	public Request create_request(LatLng position, Ride ride){
		Request data=new Request(position, this.getUsername());
		if(ride.add_request(data))
			return data;
		return null;
	}



}
