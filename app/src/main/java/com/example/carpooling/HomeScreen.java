package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity { //Pagina home para buscar viajes
    private static final int TIME_DELAY = 2000; //Tiempo maximo para poder salir con dos back_pressed
    private static long back_pressed;
    Button homePerfilButton,homeActividadButton; //Botones para moverse
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        homeActividadButton = findViewById(R.id.homeActividadButton); //Boton para moverse a actividades
        homePerfilButton = findViewById(R.id.homePerfilButton); //Boton para moverse a pefil

        homePerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //Cambiar a la actividad de profile
                Intent myIntent = new Intent(HomeScreen.this, ProfileScreen.class);
                startActivity(myIntent);
                finish();
            }
        });
        homeActividadButton.setOnClickListener(new View.OnClickListener() { //Cambiar a la actividad de actividad
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeScreen.this, ActivityScreen.class);
                startActivity(myIntent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() { //Metodo para salir presionando dos veces back_space
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
            System.exit(0);
        } else {
            Toast.makeText(getBaseContext(), "Presiona otra vez para salir",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }
}