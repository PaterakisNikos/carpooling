package com.exercise.softeng;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends AppCompatActivity {

    //Static
    public static final String TAG="SIGN IN Activity: ";
    private static final String EMPTY_FIELDS="Please fill the fields";
    private static final String EMPTY_NAME_FIELD="Please enter your username";
    private static final String EMPTY_PASS_FIELD="Please enter your password";
    private static final String NAME_NOT_FOUND="The username you enter doesn't exists";
    private static final String WRONG_CREDENTIALS="The password you enter is incorrect";


    private static final String[] WBH={NAME_NOT_FOUND, WRONG_CREDENTIALS};

    //Widgets & Views
    EditText username;
    EditText password;

    //Variables

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void SIGN_IN(View view){
        String name=username.getText().toString();//@NotNull
        String pass=password.getText().toString();//@NotNull
        //Check if any of the fields are empty
        if((name.equals(""))&&(pass.equals(""))){
            Toast.makeText(this, EMPTY_FIELDS, Toast.LENGTH_SHORT).show();
            Log.i(TAG, "name and password empties");
            return;
        }
        else if(name.equals("")){
            Toast.makeText(this, EMPTY_NAME_FIELD, Toast.LENGTH_SHORT).show();
            Log.i(TAG, "name empty");
            return;
        }
        else if(pass.equals("")){
            Log.i(TAG,"Pass empty");
            Toast.makeText(this, EMPTY_PASS_FIELD, Toast.LENGTH_SHORT).show();
            return;
        }

        //Fields are filled proceed with SIGN IN logic.
        int code=carpoolingSystem.logUser(name, pass);
        if(code!=2){
            Log.i(TAG, "Error code: "+code);
            Toast.makeText(this, WBH[code], Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(TAG, "SIGN IN successfull!");
        //User credentials were correct
        Intent intent=new Intent(this, MenuActivity.class);
        /*
        * Add variables you want to pass to the new activity if any
        *       using intent.putExtra(key, value)
        * */
        intent.putExtra("username", name);
        startActivity(intent);
    }


    private void init(){
        //get a reference to views
        username=(EditText)findViewById(R.id.editText_username);
        password=(EditText)findViewById(R.id.editText_pass);
    }

}
