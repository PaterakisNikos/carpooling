package com.exercise.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class RequestEmbarkationActivity extends AppCompatActivity {

    //Statics

    //Widgets && Views
    EditText address;

    //Variables
    private String passenger;
    private Ride ride;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_embarkation);
        init();
    }

    private void init() {
        Intent intent=getIntent();
        passenger=intent.getStringExtra("passenger");
        int index=Integer.parseInt(intent.getStringExtra("rideIndex"));
        ride=carpoolingSystem.getAvailiableRides(passenger)[index];
        address=(EditText)findViewById(R.id.editText_embarkation);
    }


    public void CREATE_REQUEST(View view){
        String address_value=address.getText().toString();
        if(address_value.equals("")){
            Toast.makeText(this, "Enter the desired address", Toast.LENGTH_SHORT).show();
            return;
        }
        LatLng coords=carpoolingSystem.getCoordsFromAddress(this, address_value);
        Request new_req=new Request(coords, passenger);
        if(!new_req.creation_successfull()){
            Toast.makeText(this, "Request creation failed", Toast.LENGTH_SHORT).show();
            return;
        }
        ride.add_request(new_req);
        go_back();
    }


    //Use this function for going back and rendering previous activity
    private void go_back(){
        Intent intent=new Intent(this, SearchRideActivity.class);
        /*
        * Add variables you want to pass to the new activity if any
        *       using intent.putExtra(key, value)
        **/
        intent.putExtra("passenger", passenger);
        startActivity(intent);
        finish();//No need to be able to come back
    }


    //Overriding back functionality because previous activity call finish()
    @Override
    public void onBackPressed(){
        go_back();
    }

}
