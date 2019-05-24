package com.exercise.softeng;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;



import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by Vasilis on 5/23/2019.
 */

public class RideAdapter extends ArrayAdapter<Ride>{

    private String passenger;
    private int image_resources[]={R.drawable.ic_reqstate_pending, R.drawable.ic_reqstate_accepted, R.drawable.ic_reqstate_declined, R.drawable.ic_reqstate_default};

    public RideAdapter(@NonNull Context context, Ride[] rides, String passenger){
        super(context, R.layout.ride_row, rides);
        this.passenger=passenger;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView=layoutInflater.inflate(R.layout.ride_row, parent, false);
        Ride current=getItem(position);
        TextView start, end, driver;
        start=(TextView)customView.findViewById(R.id.lbl_start_data);
        end=(TextView)customView.findViewById(R.id.lbl_end_data);
        driver=(TextView)customView.findViewById(R.id.lbl_driver_data);
        String address=humanReadableAddress( current.getStart().getPosition());
        String time=humanReadableDate(current.getStart().getTime());
        start.setText(address+" at: "+time);
        address=humanReadableAddress(current.getEnd().getPosition());
        time=humanReadableDate(current.getEnd().getTime());
        end.setText(address+" at: "+time);
        driver.setText(current.getDriver());
        ImageView state=(ImageView)customView.findViewById(R.id.req_stat_imageview);
        for(Request req: current.getRequests()){
            if(req.getPassenger().equals(passenger)){
                state.setImageResource(image_resources[req.getState()]);
                return customView;
            }
        }
        return customView;
    }

    //Using Stack overflow answer: https://stackoverflow.com/questions/9474121/i-want-to-get-year-month-day-etc-from-java-date-to-compare-with-gregorian-cal
    private String humanReadableDate(Date date){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Athens"));
        cal.setTime(date);
        String data=""+cal.get(Calendar.DAY_OF_MONTH)+"-"+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR)+" "+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);
        return data;
    }

    private  String humanReadableAddress(LatLng coords){
        String address=carpoolingSystem.getAddressFromCoords(getContext(),coords);
        String data=address.substring(0, address.indexOf(','));
        data+=address.substring(address.indexOf(','), address.indexOf(' ', address.indexOf(',')+2));
        return data;
    }
}
