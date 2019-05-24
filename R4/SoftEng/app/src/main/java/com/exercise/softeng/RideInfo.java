package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by Vasilis on 4/18/2019.
 */
public class RideInfo{
	private boolean is_start;
	private LatLng position;

	public boolean isIs_start() {
		return is_start;
	}

	public LatLng getPosition() {
		return position;
	}

	public Date getTime() {
		return time;
	}

	private Date time;

	public RideInfo(boolean start, LatLng position, Date time){
		is_start=start;
		this.position=position;
		this.time=time;
	}

}
