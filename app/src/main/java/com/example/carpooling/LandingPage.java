package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LandingPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
    }

    public void Btn_Sign_In(View v){
        Intent myIntent = new Intent(v.getContext(), Login.class);
        startActivity(myIntent);
        //finish();
    }

    public void Btn_Create_Account(View v){
        Intent myIntent = new Intent(v.getContext(), Register1.class);
        startActivity(myIntent);
        //finish();
    }

}