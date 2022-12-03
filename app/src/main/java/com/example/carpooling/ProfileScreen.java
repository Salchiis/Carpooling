package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileScreen extends AppCompatActivity {

        Button logOut,paypalButton;
        TextView helpButton,editProfileButton,profilePerfilButton,profileActividadButton,profileHomeButton;
        FirebaseUser user;
        DatabaseReference reference;
        String userID;
    boolean Conductor=false;

    private static final int TIME_DELAY = 2000;
        private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);
        logOut = findViewById(R.id.logOutButton);
        helpButton = findViewById(R.id.helpButton);
        editProfileButton = findViewById(R.id.editProfileButton);
        profileHomeButton = findViewById(R.id.profileHomeButton);
        profileActividadButton = findViewById(R.id.profileActividadButton);
        paypalButton = findViewById(R.id.PaypalButton);

        paypalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfileScreen.this, PaypalPage.class);
                startActivity(myIntent);
            }
        });
        profileHomeButton.setOnClickListener(new View.OnClickListener() { //para ur a home
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfileScreen.this, publicar_viaje.class);
                startActivity(myIntent);
                finish();
            }
        });

        profileActividadButton.setOnClickListener(new View.OnClickListener() {//Para ira actividad
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfileScreen.this, ActivityScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() { //Boton para hacer logout
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(ProfileScreen.this, "Log out succesfull, come back soon", Toast.LENGTH_LONG).show();

                Intent myIntent = new Intent(ProfileScreen.this, LandingPage.class);
                startActivity(myIntent);
                finish();
            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() { //Boton para ir a la pantalla de editar perfil
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfileScreen.this, EditInformation.class);
                startActivity(myIntent);
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() { //Boton para ir a las preguntas frecuentes
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ProfileScreen.this, Questions1.class);
                startActivity(myIntent);

            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();

        final TextView nameTextView = findViewById(R.id.nameAddress);
        final TextView lastnameTextView = findViewById(R.id.lastNameAddress);
        final TextView emailTextView = findViewById(R.id.emailAddress);
        final TextView phoneTextView = findViewById(R.id.phoneAddress);
        TextView modelTextView = findViewById(R.id.modelAddress);
        TextView modelAddressTitle = findViewById(R.id.modelAddressTitle);
        TextView matriculaTextView = findViewById(R.id.matriculaAddress);
        TextView matriculaAddressTitle = findViewById(R.id.matriculaAddressTitle);
        System.out.println(Conductor);
        TextView califAddressTitle = findViewById(R.id.califAddressTitle);
        TextView viajesAddressTitle = findViewById(R.id.viajesAddressTitle);
        TextView viajesAddress = findViewById(R.id.califAddress);
        TextView califAddress = findViewById(R.id.viajesAddress);

        //final TextView modelTextView =  findViewById(R.id.modelAddress);
        //final TextView placasTextView =  findViewById(R.id.placasAddress);
        //final TextView reviewsTextView =  findViewById(R.id.reviewsAddress);
        //final TextView tripsTextView =  findViewById(R.id.textViewAddress);
        //definir rating bar


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() { //Para jalar la info del usuario de la db
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    if (userProfile !=null){ //Ingresar bool si es conductor
                        String name = userProfile.name;
                        String lastname = userProfile.lastname;
                        String email = userProfile.email;
                        String phone = userProfile.phone;
                        String marca = userProfile.coche;
                        String matricula = userProfile.matricula;
                        Conductor= userProfile.isConductor;
                        nameTextView.setText(name);
                        lastnameTextView.setText(lastname);
                        emailTextView.setText(email);
                        phoneTextView.setText(phone);
                        modelTextView.setText(marca);
                        matriculaTextView.setText(matricula);
                        if (!Conductor){
                            modelTextView.setVisibility(View.GONE);
                            modelAddressTitle.setVisibility(View.GONE);
                            matriculaAddressTitle.setVisibility(View.GONE);
                            matriculaTextView.setVisibility(View.GONE);
                            califAddressTitle.setVisibility(View.GONE);
                            viajesAddressTitle.setVisibility(View.GONE);
                            viajesAddress.setVisibility(View.GONE);
                        califAddress.setVisibility(View.GONE);
                        }else{
                            modelTextView.setVisibility(View.VISIBLE);
                            modelAddressTitle.setVisibility(View.VISIBLE);
                            matriculaAddressTitle.setVisibility(View.VISIBLE);
                            matriculaTextView.setVisibility(View.VISIBLE);
                            califAddressTitle.setVisibility(View.VISIBLE);
                            viajesAddressTitle.setVisibility(View.VISIBLE);
                            viajesAddress.setVisibility(View.VISIBLE);
                            califAddress.setVisibility(View.VISIBLE);
                        }
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileScreen.this, "Algo fallo", Toast.LENGTH_SHORT).show();
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