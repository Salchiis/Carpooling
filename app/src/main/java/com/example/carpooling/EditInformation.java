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

    Button update,upgradeButton;  //Boton para hacer el update
    EditText name,lastname,phone,location; //Etiquetas a modificar
    FirebaseUser user; //Usuario activo
    FirebaseAuth mAuth; //Autentificador con firebase
    TextView editPerfilButton,editActividadButton,editHomeButton;
    DatabaseReference reference;    //Referencia  a que tablas vamos a mdooficiar
    String userID; //Guardaremos el id del usuario para saber que campo modificiar
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //Encontrar todo por ID
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        name = findViewById(R.id.nameAddress2);
        lastname = findViewById(R.id.lastnameAddress2);
        phone = findViewById(R.id.phoneAddress2);
        location = findViewById(R.id.locationAddress2);
        update = findViewById(R.id.updateButton);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users"); //Hacemos refencia a nuestra tabla Users
        userID = user.getUid();
        editPerfilButton = findViewById(R.id.editPerfilButton);
        editActividadButton = findViewById(R.id.editActividadButton);
        editHomeButton = findViewById(R.id.editHomeButton);
        upgradeButton = findViewById(R.id.upgradeButton);

        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditInformation.this, upgradeProfile.class);
                startActivity(myIntent);
               
            }
        });


        editPerfilButton.setOnClickListener(new View.OnClickListener() { //Boton que nos lleva al perfil
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditInformation.this, ProfileScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        editHomeButton.setOnClickListener(new View.OnClickListener() { //Boton que nos lleva a Home
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditInformation.this, HomeScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        editActividadButton.setOnClickListener(new View.OnClickListener() { //Boton que nos lleva a actividades
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditInformation.this, ActivityScreen.class);
                startActivity(myIntent);
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() { //Metodo para hacer update
            @Override
            public void onClick(View view) {
                //String strEmail = Email.getText().toString();
                //String strPassword = Pass.getText().toString();
                String strPhone = phone.getText().toString(); //Telofono a actualizar
                String strName = name.getText().toString(); //Nombre a actualizar
                String strLastname = lastname.getText().toString(); //Apellido a actualizar
                HashMap User = new HashMap(); //Creamos mapa de hash
                User.put("name",strName);   //Metemos nombre al maoa
                User.put("lastname",strLastname); //Metemos el apellido al mapa
                User.put("phone",strPhone);

                reference.child(userID).updateChildren(User).addOnCompleteListener(new OnCompleteListener() { //Si la operacion de update es exitosa o falla
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){ //Si es exitosa
                            Toast.makeText(EditInformation.this,"Actualización exitosa",Toast.LENGTH_LONG).show(); //Mostrar mensaje
                            Intent myIntent = new Intent(EditInformation.this, ProfileScreen.class);
                            startActivity(myIntent);
                            finish();
                        }else{
                            Toast.makeText(EditInformation.this,"Actualización fallida",Toast.LENGTH_LONG).show();//Si falla mostrara este mensaje

                        }
                    }
                });
            }
        });


    }




}