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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EditInformation extends AppCompatActivity {

    Button update,upgradeButton;  //Boton para hacer el update
    EditText name,lastname,phone,location; //Etiquetas a modificar
    FirebaseUser user; //Usuario activo
    TextView editPerfilButton,editActividadButton,editHomeButton;
    DatabaseReference reference;    //Referencia  a que tablas vamos a mdooficiar
    String userID; //Guardaremos el id del usuario para saber que campo modificiar
    private static final int TIME_DELAY = 2000;
    private static long back_pressed;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //Encontrar todo por ID
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);
        final EditText nameEditText = findViewById(R.id.nameAddress2);
        final EditText lastnameEditText = findViewById(R.id.lastnameAddress2);
        final EditText phoneEditText = findViewById(R.id.phoneAddress2);
        update = findViewById(R.id.updateButton);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users"); //Hacemos refencia a nuestra tabla Users
        userID = user.getUid();

        upgradeButton = findViewById(R.id.upgradeButton);

        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(EditInformation.this, upgradeProfile.class);
                startActivity(myIntent);
               
            }
        });




        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() { //Para jalar la info del usuario de la db
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile !=null){ //Ingresar bool si es conductor
                    String name = userProfile.name;
                    String lastname = userProfile.lastname;
                    String email = userProfile.email;
                    String phone = userProfile.phone;
                    nameEditText.setText(name);
                    lastnameEditText.setText(lastname);
                    phoneEditText.setText(phone);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EditInformation.this, "Algo fallo", Toast.LENGTH_SHORT).show();
            }
        });



        update.setOnClickListener(new View.OnClickListener() { //Metodo para hacer update
            @Override
            public void onClick(View view) {
                //String strEmail = Email.getText().toString();
                //String strPassword = Pass.getText().toString();
                String strPhone = phoneEditText.getText().toString(); //Telofono a actualizar
                String strName = nameEditText.getText().toString(); //Nombre a actualizar
                String strLastname = lastnameEditText.getText().toString(); //Apellido a actualizar
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