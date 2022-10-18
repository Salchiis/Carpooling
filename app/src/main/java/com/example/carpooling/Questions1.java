package com.example.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Questions1 extends AppCompatActivity {
    DatabaseReference reference;
    Button questionHomeButton,questionActividadButton,questionPerfilButton;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions1);
        questionHomeButton = findViewById(R.id.questionHomeButton);
        questionActividadButton = findViewById(R.id.questionActividadButton);
        questionPerfilButton = findViewById(R.id.questionPerfilButton);

        questionHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Questions1.this, HomeScreen.class);
                startActivity(myIntent);
                finish();
            }
        });
        questionActividadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Questions1.this, ActivityScreen.class);
                startActivity(myIntent);
                finish();
            }
        });
        questionPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Questions1.this, ProfileScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Preguntas");
    }




}