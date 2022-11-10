package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class upgradeProfile extends AppCompatActivity {

    FirebaseUser user; //Usuario activo
    Button uploadButton;
    DatabaseReference reference;    //Referencia  a que tablas vamos a mdooficiar
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_profile);

        final EditText modelTextView = findViewById(R.id.modelTextView);
        final EditText placasTextView = findViewById(R.id.placasTextView);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users"); //Hacemos refencia a nuestra tabla Users

        userID = user.getUid();
        uploadButton = findViewById(R.id.uploadButton);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() { //Para jalar la info del usuario de la db
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile !=null){ //Ingresar bool si es conductor
                    String placa = userProfile.matricula;
                    String modelo = userProfile.coche;
                    modelTextView.setText(modelo);
                    placasTextView.setText(placa);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(upgradeProfile.this, "Algo fallo", Toast.LENGTH_SHORT).show();
            }
        });

        uploadButton.setOnClickListener(new View.OnClickListener() { //Metodo para hacer update
            @Override
            public void onClick(View view) {
                String strPlaca = placasTextView.getText().toString(); //Nombre a actualizar
                String strModelo = modelTextView.getText().toString(); //Apellido a actualizar
                HashMap User = new HashMap(); //Creamos mapa de hash
                Boolean conductor = Boolean.TRUE;
                User.put("coche",strModelo);   //Metemos nombre al maoa
                User.put("matricula",strPlaca);
                User.put("isConductor",conductor);//Metemos el apellido al mapa
                reference.child(userID).updateChildren(User).addOnCompleteListener(new OnCompleteListener() { //Si la operacion de update es exitosa o falla
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){ //Si es exitosa
                            Toast.makeText(upgradeProfile.this,"Actualización exitosa",Toast.LENGTH_LONG).show(); //Mostrar mensaje
                            Intent myIntent = new Intent(upgradeProfile.this, ProfileScreen.class);
                            startActivity(myIntent);
                            finish();
                        }else{
                            Toast.makeText(upgradeProfile.this,"Actualización fallida",Toast.LENGTH_LONG).show();//Si falla mostrara este mensaje

                        }
                    }
                });
            }
        });
    }
}