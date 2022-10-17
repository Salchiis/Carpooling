package com.example.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class ForgetPassword extends AppCompatActivity {
    EditText emailR;
    Button resetPasswordButton;
    FirebaseAuth auth; //Autentificacion con fire base
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailR = findViewById(R.id.emailR); //Encontrar por ID por cada elemento
        resetPasswordButton = findViewById(R.id.resetPasswordB);
        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() { //Llamamos al metodo de reset password
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }
    public void resetPassword(){ //Metodo para hacer resetpassword
        String email = emailR.getText().toString(); //convertimos el email a string
        if (email.isEmpty()) {  //Verificacion que email no este vacio
            emailR.setError("Email no puede estar vacio");
            emailR.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { //Verficacion que el email sea valido
            emailR.setError("Email debe ser valido");
            emailR.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() { //Meotod para hacer reset por medio de email
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){ //Si la tarea es exitosa
                    Toast.makeText(ForgetPassword.this,"Revisa tu mail para reinciar tu contraseña",Toast.LENGTH_LONG).show(); //Mensaje para que cheque su correo
                    Intent intent = new Intent(ForgetPassword.this, Login.class); //Cambio de pantalla a login
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ForgetPassword.this,"Algo fallo, intenta nuevamente",Toast.LENGTH_LONG).show(); //En caso de fallo

                }
            }
        });
    }
}