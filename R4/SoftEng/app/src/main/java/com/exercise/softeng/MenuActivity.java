package com.exercise.softeng;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MenuActivity extends AppCompatActivity {

    //Static


    //Widgets & Views


    //Variables
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }

    private void init() {
        Intent intent=getIntent();
        username=intent.getStringExtra("username");
    }

    public void START_SEARCH_ACTIVITY(View view){
        Intent intent=new Intent(this, SearchRideActivity.class);
        /*
        * Add variables you want to pass to the new activity if any
        *       using intent.putExtra(key, value)
        * */
        intent.putExtra("passenger", username);
        startActivity(intent);
    }

    public void START_PAY_ACTIVITY(View view){
        Intent intent=new Intent(this, PayRideActivity.class);
        /*
        * Add variables you want to pass to the new activity if any
        *       using intent.putExtra(key, value)
        * */
        intent.putExtra("passenger", username);
        startActivity(intent);
    }

    public void START_MANAGE_RIDE(View view){
        Intent intent=new Intent(this, manageRideActivity.class);
        /*
         * Add variables you want to pass to the new activity if any
         *       using intent.putExtra(key, value)
         * */
        intent.putExtra("username", username);
        startActivity(intent);
    }
}
