package com.exercise.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String TAG="Sign in Not Implemented yet!";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void SIGN_IN(View view){

        /*
        *  TODO: INSERT LOGIC FOR CHECKING USERNAME AND PASSWORD
        * */
        Toast.makeText(this, TAG, Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(this, MenuActivity.class);
        /*
        * Add variables you want to pass to the new activity if any
        *       using intent.putExtra(key, value)
        * */
        startActivity(intent);
    }
}
