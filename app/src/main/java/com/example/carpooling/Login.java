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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText Email1,Pass1;
    Button SignIn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email1 = findViewById(R.id.email1);
        Pass1 = findViewById(R.id.password1);
        SignIn = findViewById(R.id.SignIn);
        mAuth = FirebaseAuth.getInstance();

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
        if (strEmail.isEmpty()){
            Email1.setError("Email can not Empty");
            Email1.requestFocus();
            return;
        } else if (strPassword.isEmpty()) {
            Pass1.setError("Enter password");
            Pass1.requestFocus();
            return;
        }else{
            mAuth.signInWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(Login.this,"Logged in succesfull",Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(Login.this, MainActivity.class);
                        startActivity(myIntent);
                        finish();
                    }else{
                        Toast.makeText(Login.this,"Logged error "+task.getException(),Toast.LENGTH_SHORT).show();



                    }
                }
            });
        }
    }
}