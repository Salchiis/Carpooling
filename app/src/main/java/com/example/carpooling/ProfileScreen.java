package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileScreen extends AppCompatActivity {

        Button logOut,helpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        logOut = findViewById(R.id.logOutButton);
        helpButton = findViewById(R.id.helpButton);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ProfileScreen.this, "Log out succesfull, come back soon", Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(ProfileScreen.this, LandingPage.class);
                startActivity(myIntent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfileScreen.this, Questions1.class);
                startActivity(myIntent);
            }
        });
    }
}