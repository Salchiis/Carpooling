package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityScreen extends AppCompatActivity { //Esta clase sirve para controlar todo lo que se vera en la pantalla de activdades
    private static final int TIME_DELAY = 2000; //Tiempo de delay para cerrar la aplicacion con dos back_pressed
    private static long back_pressed;
    Button activityPerfilScreen,activityHomeScreen; //Definición de los botones a usar para la barra de navergación
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        activityHomeScreen = findViewById(R.id.activityHomeButton);
        activityPerfilScreen = findViewById(R.id.activityPerfilButton);

        activityPerfilScreen.setOnClickListener(new View.OnClickListener() { //Cierra la actividad actual y navega a otra actividad(página)
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ActivityScreen.this, ProfileScreen.class);
                startActivity(myIntent);
                finish(); //Cierra la actividad para evitar el overflow
            }
        });

        activityHomeScreen.setOnClickListener(new View.OnClickListener() { //Cierra la actividad actual y navega a otra actividad(página)
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ActivityScreen.this, HomeScreen.class);
                startActivity(myIntent);
                finish(); //Cierra la actividad para evitar el overflow
            }
        });
    }
    @Override
    public void onBackPressed() { //Funcion para salir presionando el botón backspace dos veces seguidas
        if (back_pressed + TIME_DELAY > System.currentTimeMillis()) {
            super.onBackPressed();
            System.exit(0); //Salida del sistema
        } else {
            Toast.makeText(getBaseContext(), "Presiona otra vez para salir",
                    Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

}