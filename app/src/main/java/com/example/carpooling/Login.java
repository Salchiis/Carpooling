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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity{

    EditText Email1,Pass1;
    Button SignIn;
    FirebaseAuth mAuth;
    TextView register,forgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Metodo principal
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email1 = (EditText) findViewById(R.id.email1); //Buscar  por ids cada elemento
        Pass1 = (EditText)findViewById(R.id.password1);
        SignIn = findViewById(R.id.SignIn);
        mAuth = FirebaseAuth.getInstance();
        forgetPassword = (TextView) findViewById(R.id.forgotPassword);

        forgetPassword.setOnClickListener(new View.OnClickListener() { //Pantalla para ir a cambiar password
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Login.this, ForgetPassword.class);
                startActivity(myIntent);
            }
        });

    register = (TextView) findViewById(R.id.registerB);
    register.setOnClickListener(new View.OnClickListener() { //Pantalla para ir a registrar
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(Login.this, Register1.class);
            startActivity(myIntent);
            finish();
        }
    });


    SignIn.setOnClickListener(new View.OnClickListener() { //Meotod para hacer login
            @Override
            public void onClick(View view) {
        LoginUser();
            }
        });
    }


    private void LoginUser(){ //Metodo para login
        String strEmail = Email1.getText().toString(); //Convertir elementos a texto
        String strPassword = Pass1.getText().toString();
        if (strEmail.isEmpty()) { //Que el email no este vacio
            Email1.setError("Email no puede estar vacio");
            Email1.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) { //Asegurar que el email sea valido
            Email1.setError("Ingresa un email valido");
            Email1.requestFocus();
            return;
        }
        if (strPassword.isEmpty()) { //Que la contraseña no este vacia
            Pass1.setError("Enter password");
            Pass1.requestFocus();
            return;
        }
        if (strPassword.length() < 6) { //Que la contraseña sea mayor a 6 caracteres
            Pass1.setError("Password should be 6 characters");
            Pass1.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() { //Metodo de fire base para mandar la info
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if (user.isEmailVerified()){ //Si existe el usuario
                            Toast.makeText(Login.this,"Loggin correcto",Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(Login.this, ProfileScreen.class);
                            startActivity(myIntent);
                            finish();
                        }else{
                            user.sendEmailVerification(); //Si es la primera vez que se logea que verifique su mail
                            Toast.makeText(Login.this,"Revisa tu mail para verificar tu cuenta",Toast.LENGTH_LONG).show();

                        }


                    }else{ //Error en caso de no poder conseguir la instancia del usuario
                        Toast.makeText(Login.this,"Logged error ",Toast.LENGTH_SHORT).show();



                    }
                }
            });
        }
    }
