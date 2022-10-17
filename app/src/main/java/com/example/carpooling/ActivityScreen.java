package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityScreen extends AppCompatActivity {
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    Button activityPerfilScreen,activityHomeScreen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        activityHomeScreen = findViewById(R.id.activityHomeButton);
        activityPerfilScreen = findViewById(R.id.activityPerfilButton);

        activityPerfilScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ActivityScreen.this, ProfileScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        activityHomeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ActivityScreen.this, HomeScreen.class);
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