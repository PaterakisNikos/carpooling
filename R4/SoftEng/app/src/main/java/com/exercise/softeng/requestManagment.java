package com.exercise.softeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class requestManagment extends AppCompatActivity {

    private String ride;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_management);
        init();
    }

    private void init() {
        Intent intent=getIntent();
        ride=intent.getStringExtra("ride");
        index=Integer.parseInt(intent.getStringExtra("rideIndex"));

    }
}
