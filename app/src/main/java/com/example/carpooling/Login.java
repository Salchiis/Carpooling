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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email1 = (EditText) findViewById(R.id.email1);
        Pass1 = (EditText)findViewById(R.id.password1);
        SignIn = findViewById(R.id.SignIn);
        mAuth = FirebaseAuth.getInstance();
        forgetPassword = (TextView) findViewById(R.id.forgotPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Login.this, ForgetPassword.class);
                startActivity(myIntent);
            }
        });

    register = (TextView) findViewById(R.id.registerB);
    register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(Login.this, Register1.class);
            startActivity(myIntent);
            //finish();
        }
    });


    SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        LoginUser();
            }
        });
    }


    private void LoginUser(){
        String strEmail = Email1.getText().toString();
        String strPassword = Pass1.getText().toString();
        if (strEmail.isEmpty()) {
            Email1.setError("Email can not Empty");
            Email1.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            Email1.setError("Provide a valid mail pls");
            Email1.requestFocus();
            return;
        }
        if (strPassword.isEmpty()) {
            Pass1.setError("Enter password");
            Pass1.requestFocus();
            return;
        }
        if (strPassword.length() < 6) {
            Pass1.setError("Password should be 6 characters");
            Pass1.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                        if (user.isEmailVerified()){
                            Toast.makeText(Login.this,"Logged in succesfull",Toast.LENGTH_LONG).show();
                            Intent myIntent = new Intent(Login.this, ProfileScreen.class);
                            startActivity(myIntent);
                            //finish();
                        }else{
                            user.sendEmailVerification();
                            Toast.makeText(Login.this,"Check email to verify account",Toast.LENGTH_LONG).show();

                        }


                    }else{
                        Toast.makeText(Login.this,"Logged error ",Toast.LENGTH_SHORT).show();



                    }
                }
            });
        }
    }
