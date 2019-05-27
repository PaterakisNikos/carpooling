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

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * Created by Vasilis on 5/23/2019.
 */

public class RideAdapter extends ArrayAdapter<Ride>{

    private String passenger;
    //private int image_resources[]={R.drawable.ic_reqstate_pending, R.drawable.ic_reqstate_accepted, R.drawable.ic_reqstate_declined, R.drawable.ic_reqstate_default};

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
                switch (req.getState()){
                    case Request.ACCEPTED_REQUEST:
                        state.setImageResource(ImageResources.CHECK);
                        break;
                    case Request.PENDING_REQUEST:
                        state.setImageResource(ImageResources.QUESTION_MARK);
                        break;
                    case Request.DECLINED_REQUEST:
                        state.setImageResource(ImageResources.CROSS);
                        break;
                    default:
                        state.setImageResource(ImageResources.PLUS_SIGN);
                        break;
                }
            }
        }
        return customView;
    }

    //Using Stack overflow answer: https://stackoverflow.com/questions/9474121/i-want-to-get-year-month-day-etc-from-java-date-to-compare-with-gregorian-cal
    private String humanReadableDate(LocalDateTime date) {
        String data="";
        int month=(date.getMonthValue());
        int day=(date.getDayOfMonth());
        int hour=(date.getHour());
        int minutes=(date.getMinute());
        if(day<10)data+="0"+day+"-";
        else data+=day+"-";
        if(month<10)data+="0"+month+"-";
        else data+=month+"-";
        data+=date.getYear()+" ";
        if(hour<10)data+="0"+hour+":";
        else data+=hour+":";
        if(minutes<10)data+="0"+minutes;
        else data+=minutes;
        return data;
    }


    //Don't know if it works well for addresses outside of GREECE
    private  String humanReadableAddress(LatLng coords){
        String address= carpoolingSystem.getAddressFromCoords(getContext(),coords);
        String data=address.substring(0, address.indexOf(','));
        data+=address.substring(address.indexOf(','), address.indexOf(' ', address.indexOf(',')+2));
        return data;
    }
}
