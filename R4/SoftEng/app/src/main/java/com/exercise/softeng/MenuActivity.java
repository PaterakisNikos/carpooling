package com.exercise.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


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
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
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
}
