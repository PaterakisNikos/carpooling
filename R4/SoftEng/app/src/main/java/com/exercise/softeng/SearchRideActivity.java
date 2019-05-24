package com.exercise.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class SearchRideActivity extends AppCompatActivity {

    //Statics
    public static final String TAG="SearchRideActivity: ";

    //Widgets && Views
    ListView rides_wrapper;

    //Variables
    private String passenger;
    private Ride[] available_rides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ride);
        init();
    }

    private void init() {
        Intent intent=getIntent();
        passenger=intent.getStringExtra("passenger");
        rides_wrapper=(ListView)findViewById(R.id.listview_rides_wrapper);
        Toast.makeText(this, passenger, Toast.LENGTH_SHORT).show();
        updateList();
        rides_wrapper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(SearchRideActivity.this, RequestEmbarkationActivity.class);
                /*
                * Add variables you want to pass to the new activity if any
                *       using intent.putExtra(key, value)
                * */
                intent.putExtra("passenger", passenger);
                intent.putExtra("rideIndex", ""+i);
                startActivity(intent);
                finish();//Finish this activity we want to rendered after an Embarkation Request
            }
        });
    }


    public void updateList(){
        available_rides=carpoolingSystem.getAvailiableRides(passenger);
        Log.i(TAG, "Rides length: "+available_rides.length);
        RideAdapter adapter=new RideAdapter(this, available_rides, passenger);
        rides_wrapper.setAdapter(adapter);
    }
}
