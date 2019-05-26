package com.exercise.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ManageRequestsActivity extends AppCompatActivity implements RenderListener{

    //Statics

    //Widgets && Views
    ListView request_list;

    //Variables
    Ride ride;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_requests);
        init();
    }

    private void init() {
        Intent intent=getIntent();
        int rideID=Integer.parseInt(intent.getStringExtra("rideIndex"));
        request_list=(ListView)findViewById(R.id.listView_requests_wrapper);
        ride=carpoolingSystem.getRide(rideID);
        updateList();
    }

    private void updateList() {
        Request[] requests=ride.getRequests();
        RequestAdapter adapter=new RequestAdapter(this, requests, ride);
        adapter.attachListener(this);
        request_list.setAdapter(adapter);
    }


    @Override
    public void re_render() {updateList();}
}
