package com.exercise.softeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class manageRideActivity extends AppCompatActivity implements RenderListener{

    //Statics

    //Widgets && Views
    ListView listView;

    //Variables
    private String username;
    private Ride[] rides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_ride);

        Intent intent=getIntent();
        username = intent.getStringExtra("username");

        listView = findViewById(R.id.listView);


        rides=carpoolingSystem.getOwnedRides(username);
        //ArrayAdapter<Ride> adapter = new ArrayAdapter<Ride>(this, android.R.layout.simple_list_item_1, ride);

        //listView.setAdapter(adapter);

        updateList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(manageRideActivity.this, ManageRequestsActivity.class);
                /*
                 * Add variables you want to pass to the new activity if any
                 *       using intent.putExtra(key, value)
                 * */
                intent.putExtra("rideIndex", "" + rides[i].ID);
                startActivity(intent);
                //finish();//Finish this activity we want to rendered after an Embarkation Request
            }

        });


    }

    private void updateList(){
        ManageAdapter adapter=new ManageAdapter(this, rides, username);
        adapter.attachListner(this);
        listView.setAdapter(adapter);
    }


    @Override
    public void re_render() {
        updateList();
    }

    public void CREATE_RIDE(View view){
        Intent intent = new Intent(manageRideActivity.this, createRide.class);
                /*
                 * Add variables you want to pass to the new activity if any
                 *       using intent.putExtra(key, value)
                 * */
        intent.putExtra("driver", username);
        startActivity(intent);
        finish();
    }


}
