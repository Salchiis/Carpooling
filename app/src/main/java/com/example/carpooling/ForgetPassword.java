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
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        emailR = findViewById(R.id.emailR);
        resetPasswordButton = findViewById(R.id.resetPasswordB);
        auth = FirebaseAuth.getInstance();

        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }
    public void resetPassword(){
        String email = emailR.getText().toString();
        if (email.isEmpty()) {
            emailR.setError("Email can not Empty");
            emailR.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailR.setError("Provide a valid mail pls");
            emailR.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetPassword.this,"Check your email to reset your password",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgetPassword.this, Login.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(ForgetPassword.this,"Algo fallo",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}