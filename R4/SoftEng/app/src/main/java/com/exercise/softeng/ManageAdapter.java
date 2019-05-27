package com.exercise.softeng;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ManageAdapter extends ArrayAdapter<Ride> {

    private String driver;


    ManageAdapter(Context context, Ride[] rides, String driver){
        super(context, R.layout.manage_row, rides);
        this.driver=driver;
    }

    RenderListener executor;

    public void attachListner(Activity activity){ executor=(RenderListener)activity; }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView=layoutInflater.inflate(R.layout.manage_row, parent, false);
        Ride ride=getItem(position);
        TextView start=customView.findViewById(R.id.lbl_data);
        ImageView img_view=customView.findViewById(R.id.imageView_reqNotification);
        start.setText(humanReadableDate(ride.getStart().getTime()));
        for(Request req: ride.getRequests()){
            if(req.getState()==Request.PENDING_REQUEST) {
                img_view.setImageResource(ImageResources.QUESTION_MARK);
                break;
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
}
