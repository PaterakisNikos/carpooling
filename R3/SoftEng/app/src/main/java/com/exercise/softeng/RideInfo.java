package com.exercise.softeng;

import com.google.android.gms.maps.model.LatLng;
import java.util.Date;

/**
 * Created by Vasilis on 4/18/2019.
 */
public class RideInfo {
	public boolean is_start;
	public LatLng position;
	public Date time;

	public RideInfo(boolean start, LatLng position, Date time){
		is_start=start;
		this.position=position;
		this.time=time;
	}

}
