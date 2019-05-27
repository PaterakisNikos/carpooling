package com.exercise.softeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class createRide extends AppCompatActivity {

    //Static
    private static final String EMPTY_FIELDS="Please fill the fields";
    private static final String WRONG_FORMAT="Please give the right format";
    private static final String EMPTY_DESTINATION="Please fill the destination field";
    private static final String EMPTY_START_POSITION="Please fill the start field";
    private static final String EMPTY_ARRIVAL_TIME="Please fill the arrival time field";
    private static final String EMPTY_START_TIME="Please fill the start time field";
    private static final String EMPTY_DATE="Please fill the date field";
    private static final String EMPTY_COST="Please fill the cost field";
    private static final String MUST_BE_POSITIVE="You must give an amount greater of 0";
    private static final String EMPTY_NUM_PASSENGERS="You must specify the available passengers";
    //Widgets & Views
    EditText startPos;
    EditText dest;
    EditText date;
    EditText startingTime;
    EditText arrivalTime;
    EditText cost;
    EditText numOfPassengers;
    //Variables
    String driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creat_ride);
        init();
    }

    public void CREATE_RIDE(View view) {
        String startPosition=startPos.getText().toString();//@NonNull
        String destination=dest.getText().toString();//@NonNull
        String r_date=date.getText().toString(); //@NonNull
        String s_Time=startingTime.getText().toString(); //@NonNull
        String a_Time=arrivalTime.getText().toString(); //NonNull
        String r_cost=cost.getText().toString();//@NonNull
        String nop=numOfPassengers.getText().toString();//@NonNull
        //Empty checks started
        if(startPosition.equals("")&&destination.equals("")&&r_date.equals("")&&s_Time.equals("")&&a_Time.equals("")&&r_cost.equals("")&&nop.equals("")) {
            Toast.makeText(this, EMPTY_FIELDS, Toast.LENGTH_SHORT).show();
            return;
        }

        if(destination.equals("")){Toast.makeText(this, EMPTY_DESTINATION, Toast.LENGTH_SHORT).show();return;}
        if(startPosition.equals("")){Toast.makeText(this, EMPTY_START_POSITION, Toast.LENGTH_SHORT).show();return;}
        if(r_date.equals("")){Toast.makeText(this, EMPTY_DATE, Toast.LENGTH_SHORT).show();return;}
        if(s_Time.equals("")){Toast.makeText(this, EMPTY_START_TIME, Toast.LENGTH_SHORT).show();return;}
        if(a_Time.equals("")){Toast.makeText(this, EMPTY_ARRIVAL_TIME, Toast.LENGTH_SHORT).show();return;}
        if(r_cost.equals("")){Toast.makeText(this, EMPTY_COST, Toast.LENGTH_SHORT).show();return;}
        if(nop.equals("")){Toast.makeText(this, EMPTY_NUM_PASSENGERS, Toast.LENGTH_SHORT).show();return;}
        //Empty checks ended

        //All fields are filled
        LocalDate DATE=stringToDate(r_date);
        LocalTime START_TIME, END_TIME;
        START_TIME=stringToTime(s_Time);
        END_TIME=stringToTime(a_Time);

        //Checking if the formats where corrects
        if (DATE==null||START_TIME==null||END_TIME==null){
            Toast.makeText(this, WRONG_FORMAT, Toast.LENGTH_SHORT).show();
            return;
        }
        if (Integer.parseInt(r_cost)<=0) {
            Toast.makeText(this, MUST_BE_POSITIVE, Toast.LENGTH_SHORT).show();
            return;
        }

        LocalDateTime START_TS, END_TS;
        START_TS=LocalDateTime.of(DATE, START_TIME);
        END_TS=LocalDateTime.of(DATE, END_TIME);
        LatLng START_POSITION, END_POSITION;
        START_POSITION=carpoolingSystem.getCoordsFromAddress(this,startPosition);
        END_POSITION=carpoolingSystem.getCoordsFromAddress(this,destination);
        double PRICE=Double.parseDouble(r_cost);
        int passNumber=Integer.parseInt(nop);

        boolean flag=carpoolingSystem.CREATE_RIDE(driver, START_POSITION, END_POSITION, START_TS, END_TS, PRICE, passNumber);
        if(carpoolingSystem.CREATE_RIDE(driver, START_POSITION, END_POSITION, START_TS, END_TS, PRICE, passNumber)){
            Toast.makeText(this,""+ flag , Toast.LENGTH_SHORT).show();
            //go_back();
        }
    }

    private void go_back() {
        Intent intent = new Intent(this, manageRideActivity.class);
        /*
         * Add variables you want to pass to the new activity if any
         *       using intent.putExtra(key, value)
         * */
        intent.putExtra("username", driver);
        startActivity(intent);
        finish();
    }

    //Overriding back functionality because previous activity call finish() and we want to re-render it
    @Override
    public void onBackPressed(){
        go_back();
    }




    private void init(){
        //get a reference to views
        Intent intent=getIntent();
        driver=intent.getStringExtra("driver");
        startPos=findViewById(R.id.editText_start);
        dest=findViewById(R.id.editText_destination);
        date=findViewById(R.id.editText_date);
        startingTime=findViewById(R.id.editText_startTime);
        arrivalTime=findViewById(R.id.editText_arrivalTime);
        cost=findViewById(R.id.editText_cost);
        numOfPassengers=findViewById(R.id.editText_num_ofpassengers);
    }

    @Nullable
    public static LocalDate stringToDate(String data) {
        LocalDate date=null;
        if(data.charAt(2)!='-'||data.charAt(5)!='-')return date;
        try {date=LocalDate.of(Integer.parseInt(data.substring(6)), Integer.parseInt(data.substring(3, 5)), Integer.parseInt(data.substring(0, 2)));}
        catch(Exception e) {return date;}
        return date;
    }

    @Nullable
    public static LocalTime stringToTime(String data) {
        LocalTime time=null;
        if(data.charAt(2)!=':')return time;
        try {time=LocalTime.of(Integer.parseInt(data.substring(0, 2)), Integer.parseInt(data.substring(3)));}
        catch(Exception e) { return time;}
        return time;
    }

}
