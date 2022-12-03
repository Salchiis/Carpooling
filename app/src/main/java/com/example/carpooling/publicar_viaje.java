package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class publicar_viaje extends AppCompatActivity {
    TextView profilePerfilButton3,profileActividadButton3,profileHomeButton3,texto9,texto10,texto1,texto2,texto3,texto4,texto5,texto6,texto7,texto8;
    Spinner spinnerLlegada,spinnerSalida;
    EditText fechaInput,horaInput,pasajerosInput,precioInput;
    Button publicarButton;
    FirebaseAuth mAuth;
    DatabaseReference reference;
    FirebaseUser user;
    String userID;
    boolean Conductor=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicar_viaje);
        spinnerLlegada = findViewById(R.id.llegadaSpinner);
        spinnerSalida = findViewById(R.id.salidaSpinner);
        fechaInput = findViewById(R.id.fechaInput);
        horaInput = findViewById(R.id.horaInput);
        pasajerosInput = findViewById(R.id.pasajerosInput);
        precioInput = findViewById(R.id.precioInput);
        publicarButton = findViewById(R.id.publicarButton);
        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);
        texto3 = findViewById(R.id.texto3);
        texto4 = findViewById(R.id.texto4);
        texto5 = findViewById(R.id.texto5);
        texto6 = findViewById(R.id.texto6);
        texto7 = findViewById(R.id.texto7);
        texto8 = findViewById(R.id.texto8);
        texto9 = findViewById(R.id.texto9);
        texto10 = findViewById(R.id.texto10);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        profileHomeButton3 = findViewById(R.id.profileHomeButton3);
        profileActividadButton3 = findViewById(R.id.profileProfileButton3);
        profilePerfilButton3 = findViewById(R.id.button7);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.salidas, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSalida.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.llegadas, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLlegada.setAdapter(adapter2);

        publicarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTrip();
            }
        });

        profileHomeButton3.setOnClickListener(new View.OnClickListener() { //para ur a home
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(publicar_viaje.this, publicar_viaje.class);
                startActivity(myIntent);
                finish();
            }
        });

        profilePerfilButton3.setOnClickListener(new View.OnClickListener() { //para ur a home
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(publicar_viaje.this, ProfileScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        profileActividadButton3.setOnClickListener(new View.OnClickListener() {//Para ira actividad
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(publicar_viaje.this, ActivityScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() { //Para jalar la info del usuario de la db
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile !=null){ //Ingresar bool si es conductor

                    Conductor= userProfile.isConductor;

                    if (!Conductor){
                        publicarButton.setVisibility(View.GONE);
                        spinnerLlegada.setVisibility(View.GONE);
                        spinnerSalida.setVisibility(View.GONE);;
                        fechaInput.setVisibility(View.GONE);
                        horaInput.setVisibility(View.GONE);
                        pasajerosInput.setVisibility(View.GONE);
                        precioInput.setVisibility(View.GONE);
                        publicarButton.setVisibility(View.GONE);
                        texto1.setVisibility(View.GONE);
                        texto2.setVisibility(View.GONE);
                        texto3.setVisibility(View.GONE);
                        texto4.setVisibility(View.GONE);
                        texto5.setVisibility(View.GONE);
                        texto6.setVisibility(View.GONE);
                        texto7.setVisibility(View.GONE);
                        texto8.setVisibility(View.GONE);
                        texto9.setVisibility(View.VISIBLE);
                        texto10.setVisibility(View.VISIBLE);

                    }else{
                        publicarButton.setVisibility(View.VISIBLE);
                        spinnerLlegada.setVisibility(View.VISIBLE);
                        spinnerSalida.setVisibility(View.VISIBLE);;
                        fechaInput.setVisibility(View.VISIBLE);
                        horaInput.setVisibility(View.VISIBLE);
                        pasajerosInput.setVisibility(View.VISIBLE);
                        precioInput.setVisibility(View.VISIBLE);
                        publicarButton.setVisibility(View.VISIBLE);
                        texto1.setVisibility(View.VISIBLE);
                        texto2.setVisibility(View.VISIBLE);
                        texto3.setVisibility(View.VISIBLE);
                        texto4.setVisibility(View.VISIBLE);
                        texto5.setVisibility(View.VISIBLE);
                        texto6.setVisibility(View.VISIBLE);
                        texto7.setVisibility(View.VISIBLE);
                        texto8.setVisibility(View.VISIBLE);
                        texto9.setVisibility(View.GONE);
                        texto10.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(publicar_viaje.this, "Algo fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createTrip() { //Pasamos los campos a string

        Calendar calendar = Calendar.getInstance();
        //int precio = calendar.get(Calendar.DATE);


        String llegada = spinnerLlegada.getSelectedItem().toString();
        String salida = spinnerSalida.getSelectedItem().toString();
        String fecha = fechaInput.getText().toString();
        String hora = horaInput.getText().toString();
        int pasajeros = Integer.parseInt(pasajerosInput.getText().toString());
        int precio = Integer.parseInt(precioInput.getText().toString());
        int tiempo = 0;
        if (llegada.equals("Paseo Destino")){ //Podria haber hecho un switch pero se me olvido que exisitian
            tiempo = 5;
        }else if (llegada.equals("La casita del profe")){
            tiempo = 90;
        }else if (llegada.equals("Walmart Angelopolis")){
            tiempo = 10;
        }else if (llegada.equals("Sonata")){
            tiempo =  20;
        }else if (llegada.equals("Angelopolis")){
            tiempo = 10;
        }else if (llegada.equals("Villas de atlixco")){
            tiempo = 5;
        }else if (llegada.equals("Frikiplaza")){
            tiempo = 25;
        }else if (llegada.equals("Avenida Juarez")){
            tiempo= 15;
        }else if (llegada.equals("Palas Store")){
            tiempo = 20;
        }

        Boolean active = true;
        Boolean finished = false;

        if (fecha.isEmpty()) {
            fechaInput.setError("Fecha no valida");
            fechaInput.requestFocus();
            return;
        }


        Trip trip = new Trip(salida, precio, llegada, hora, fecha, active, finished, pasajeros,tiempo,userID); //Creamos un objeto en case a nuestra informacion de los campos
         reference = FirebaseDatabase.getInstance().getReference().child("Viajes");

         reference.push().setValue(trip);
        Toast.makeText(publicar_viaje.this, "Viaje creado", Toast.LENGTH_LONG).show(); //Mensaje de exito
        Intent intent = new Intent(publicar_viaje.this, ActivityScreen.class);
        startActivity(intent);
        finish();


    }

    }
