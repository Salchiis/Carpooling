package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register1 extends AppCompatActivity {
    EditText Email,Pass,Phone,Name,LastName,Photo;
    Button SignUp;
    FirebaseAuth mAuth;
    TextView Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Creacion para el metodo de registrar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        Email = findViewById(R.id.email);
        Pass = findViewById(R.id.password);
        Phone = findViewById(R.id.phone);
        Name = findViewById(R.id.name);
        LastName = findViewById(R.id.lastname);
        SignUp = findViewById(R.id.SignUp);
        Login = (TextView) findViewById(R.id.loginb);
        mAuth = FirebaseAuth.getInstance();


        Login.setOnClickListener(new View.OnClickListener() { //Boton para ir a login por cualquier cosa
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Register1.this, Login.class);
                startActivity(myIntent);
                finish();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() { //Bton para mandar la info y registrarse
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void createUser() { //Pasamos los campos a string
        String strEmail = Email.getText().toString();
        String strPassword = Pass.getText().toString();
        String strPhone = Phone.getText().toString();
        String strName = Name.getText().toString();
        String strLastname = LastName.getText().toString();
        String strMatricula = "";
        String strCoche = "";
        Boolean strConductor = false;

        if (strEmail.isEmpty()) { //Verificacion de la info
            Email.setError("Email can not Empty");
            Email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            Email.setError("Provide a valid mail pls");
            Email.requestFocus();
            return;
        }
        if (strPassword.isEmpty()) {
            Pass.setError("Enter password");
            Pass.requestFocus();
            return;
        }
        if (strPassword.length() < 6) {
            Pass.setError("Password should be 6 characters");
            Pass.requestFocus();
            return;
        }

        if (strPhone.isEmpty()) {
            Phone.setError("Enter Phone");
            Phone.requestFocus();
            return;
        }
        if (strName.isEmpty()) {
            Name.setError("Enter Name");
            Name.requestFocus();
            return;
        }
        if (strLastname.isEmpty()) {
            LastName.setError("Enter Lastname");
            LastName.requestFocus();
            return;

        }


        mAuth.createUserWithEmailAndPassword(strEmail, strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() { //Metodo de firebase para crear usuario
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) { //Si es correcto todo
                    User user = new User(strName,strLastname,strPhone,strEmail,strCoche,strMatricula,strConductor); //Creamos un objeto en case a nuestra informacion de los campos
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() { //Elegimos la db donde guardar nuestra info, al igual que definir al usuario y asignarle un id
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register1.this, "Usuario creado", Toast.LENGTH_LONG).show(); //Mensaje de exito
          Intent intent = new Intent(Register1.this, LandingPage.class);
                    startActivity(intent);
                    finish();
                            }else{
                                Toast.makeText(Register1.this, "Hubo un error intenta nuevamente", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                } else { //Si surge error al registrar
                    Toast.makeText(Register1.this, "Hubo un error intenta nuevamente", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    }



    /*       Intent intent = new Intent(Register1.this, Login.class);
                    startActivity(intent);
                    finish();*/