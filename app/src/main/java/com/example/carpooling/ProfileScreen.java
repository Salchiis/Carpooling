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

        Button logOut;
        TextView helpButton;
        FirebaseUser user;
        DatabaseReference reference;
        String userID;
        private static final int TIME_DELAY = 2000;
        private static long back_pressed;
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
                finish();
            }
        });


        helpButton.setOnClickListener(new View.OnClickListener() {
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

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userProfile = snapshot.getValue(User.class);
                    if (userProfile !=null){
                        String name = userProfile.name;
                        String lastname = userProfile.lastname;
                        String email = userProfile.email;
                        String phone = userProfile.phone;
                        nameTextView.setText(name);
                        lastnameTextView.setText(lastname);
                        emailTextView.setText(email);
                        phoneTextView.setText(phone);
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