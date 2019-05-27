package com.exercise.softeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class createRide extends AppCompatActivity {

    //Static
    private static final String EMPTY_FIELDS="Please fill the fields";
    private static final String WRONG_FORMAT="Please give the right format";
    private static final String EMPTY_DESTINATION="Please fill the destination field";
    private static final String EMPTY_START_POSITION="Please fill the start field";
    private static final String EMPTY_ARRIVAL_TIME="Please fill the arrival time field";
    private static final String EMPTY_START_TIME="Please fill the start time field";
    private static final String EMPTY_COST="Please fill the cost field";

    //Widgets & Views
    EditText startPos;
    EditText dest;
    EditText date;
    EditText startingTime;
    EditText arrivalTime;
    EditText cost;

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


        if(startPosition.equals("")&&destination.equals("")&&r_date.equals("")&&s_Time.equals("")&&a_Time.equals("")&&r_cost.equals("")) {
            Toast.makeText(this, EMPTY_FIELDS, Toast.LENGTH_SHORT).show();
            return;
        }

        if (r_date.charAt(2)!='-' || r_date.charAt(5)!='-' ) {
            Toast.makeText(this, WRONG_FORMAT, Toast.LENGTH_SHORT).show();
            return;
        }

        if (s_Time.charAt(2)!=':') {
            Toast.makeText(this, WRONG_FORMAT, Toast.LENGTH_SHORT).show();
            return;
        }

        if (a_Time.charAt(2)!=':') {
            Toast.makeText(this, WRONG_FORMAT, Toast.LENGTH_SHORT).show();
            return;
        }

        if (Integer.parseInt(r_cost)<=0) {
            Toast.makeText(this, "You must give an amount greater of 0 ", Toast.LENGTH_SHORT).show();
        }


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


    }

}
