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

public class Register1 extends AppCompatActivity {
    EditText Email,Pass;
    Button SignUp;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register1);
        Email = findViewById(R.id.email);
        Pass = findViewById(R.id.password);
        SignUp = findViewById(R.id.SignUp);

        mAuth = FirebaseAuth.getInstance();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void createUser(){
        String strEmail = Email.getText().toString();
        String strPassword = Pass.getText().toString();
        if (strEmail.isEmpty()){
            Email.setError("Email can not Empty");
            Email.requestFocus();
            return;
        } else if (strPassword.isEmpty()) {
            Pass.setError("Enter password");
            Pass.requestFocus();
            return;
        }else{
        mAuth.createUserWithEmailAndPassword(strEmail,strPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   Toast.makeText(Register1.this,"User Created",Toast.LENGTH_SHORT).show();
                   Intent intent= new Intent(Register1.this,Login.class);
                    startActivity(intent);
                    finish();

                }else{
                    Toast.makeText(Register1.this,"Error "+task.getException(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        }
    }

}