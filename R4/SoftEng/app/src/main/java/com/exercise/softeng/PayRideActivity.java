package com.exercise.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PayRideActivity extends AppCompatActivity {

    //Statics

    //Widgets && Views

    //Variables
    private String passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ride);
        init();
    }

    private void init() {
        Intent intent=getIntent();
        passenger=intent.getStringExtra("passenger");
    }


}
