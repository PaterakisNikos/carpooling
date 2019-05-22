package com.exercise.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


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
}
