package com.exercise.softeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

public class PayRideActivity extends AppCompatActivity implements PayAdapter.RenderListener {

    //Statics
    public static final String TAG="PayRideActivity: ";
    //Widgets && Views
    ListView payRides;

    //Variables
    private String passenger;
    private Ride[] available_payrides;
    private PayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_ride);
        init();
    }

    private void init() {
        Intent intent=getIntent();
        passenger=intent.getStringExtra("passenger");
        available_payrides= carpoolingSystem.availableForPay(passenger);
        payRides=(ListView)findViewById(R.id.listview_payride_wrapper);
        updateList();

    }


    //Use this method to re-render PayRideActivity
    private void updateList() {
        adapter=new PayAdapter(this, available_payrides, passenger);
        adapter.attachRenderListener(this);
        payRides.setAdapter(adapter);
    }


    @Override
    public void re_render() {
        updateList();
    }
}
