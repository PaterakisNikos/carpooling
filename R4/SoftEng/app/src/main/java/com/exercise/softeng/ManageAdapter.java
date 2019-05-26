package com.exercise.softeng;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ManageAdapter extends ArrayAdapter<Ride> {

    private String driver;

    ManageAdapter(Context context, Ride[] rides, String driver){
        super(context, R.layout.manage_row, rides);
        this.driver=driver;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(getContext());
        View customView=layoutInflater.inflate(R.layout.manage_row, parent, false);
        Ride ride=getItem(position);
        TextView start=customView.findViewById(R.id.lbl_data);
        start.setText(humanReadableDate(ride.getStart().getTime()));
        return customView;
    }


    //TODO one of two vars bellow don't need +1
    //Using Stack overflow answer: https://stackoverflow.com/questions/9474121/i-want-to-get-year-month-day-etc-from-java-date-to-compare-with-gregorian-cal
    private String humanReadableDate(Date date){
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Athens"));
        cal.setTime(date);
        String data="";
        int month=(cal.get(Calendar.MONTH)+1);
        int day=(cal.get(Calendar.DAY_OF_MONTH)+1);
        int hour=(cal.get(Calendar.HOUR_OF_DAY));
        int minutes=(cal.get(Calendar.MINUTE));
        if(day<10)data+="0"+day+"-";
        else data+=day+"-";
        if(month<10)data+="0"+month+"-";
        else data+=month+"-";
        data+=cal.get(Calendar.YEAR)+" ";
        if(hour<10)data+="0"+hour+":";
        else data+=hour+":";
        if(minutes<10)data+="0"+minutes;
        else data+=minutes;
        return data;
    }
}
