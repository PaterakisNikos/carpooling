package com.exercise.softeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import static com.exercise.softeng.carpoolingSystem.init;

public class manageRideActivity extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_ride);
        ListView listView = findViewById(R.id.listView);

        final ArrayList<Ride> ride = new ArrayList<>();
        for (Ride r : carpoolingSystem.getAvailiableRides("")) {
            ride.add(r);
        }
        ArrayAdapter<Ride> adapter = new ArrayAdapter<Ride>(this, android.R.layout.simple_list_item_1, ride);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(manageRideActivity.this, requestManagment.class);
                /*
                 * Add variables you want to pass to the new activity if any
                 *       using intent.putExtra(key, value)
                 * */

                intent.putExtra("Ride", ride);
                intent.putExtra("rideIndex", "" + i);
                startActivity(intent);
                finish();//Finish this activity we want to rendered after an Embarkation Request
            }

        });


        init();

    }



    private void init() {
        Intent intent=getIntent();
        username = intent.getStringExtra("username");
    }
}
