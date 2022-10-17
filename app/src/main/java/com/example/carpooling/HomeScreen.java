package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    Button homePerfilButton,homeActividadButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        homeActividadButton = findViewById(R.id.homeActividadButton);
        homePerfilButton = findViewById(R.id.homePerfilButton);

        homePerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeScreen.this, ProfileScreen.class);
                startActivity(myIntent);
                finish();
            }
        });
        homeActividadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeScreen.this, ActivityScreen.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Press once again to exit!",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}