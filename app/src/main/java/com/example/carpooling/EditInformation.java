package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditInformation extends AppCompatActivity {

    Button update;
    EditText name,lastname,phone,location;
    FirebaseUser user;
    FirebaseAuth mAuth;
TextView editPerfilButton,editActividadButton,editHomeButton;
    DatabaseReference reference;
    String userID;
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        name = findViewById(R.id.nameAddress2);
        lastname = findViewById(R.id.lastnameAddress2);
        phone = findViewById(R.id.phoneAddress2);
        location = findViewById(R.id.locationAddress2);
        update = findViewById(R.id.updateButton);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        editPerfilButton = findViewById(R.id.editPerfilButton);
        editActividadButton = findViewById(R.id.editActividadButton);
        editHomeButton = findViewById(R.id.editHomeButton);

        editPerfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditInformation.this, ProfileScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        editHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditInformation.this, HomeScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        editActividadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditInformation.this, ActivityScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String strEmail = Email.getText().toString();
                //String strPassword = Pass.getText().toString();
                String strPhone = phone.getText().toString();
                String strName = name.getText().toString();
                String strLastname = lastname.getText().toString();
                HashMap User = new HashMap();
                User.put("name",strName);
                User.put("lastname",strLastname);
                User.put("phone",strPhone);

                reference.child(userID).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditInformation.this,"Update in succesfull",Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(EditInformation.this, ProfileScreen.class);
                            startActivity(myIntent);
                            finish();
                        }else{
                            Toast.makeText(EditInformation.this,"Failed Update",Toast.LENGTH_LONG).show();

                        }
                    }
                });
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