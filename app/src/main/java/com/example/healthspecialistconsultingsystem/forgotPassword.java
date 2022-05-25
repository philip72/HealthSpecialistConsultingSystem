package com.example.healthspecialistconsultingsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotPassword extends AppCompatActivity {
    private EditText emailpas;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        emailpas = (EditText) findViewById(R.id.editTextTextEmailAddress);
        Button reset = (Button) findViewById(R.id.reset_password);
        reset.setOnClickListener(view->{
            resetPassword();
        });

        auth = FirebaseAuth.getInstance();

    }

    private void resetPassword() {
        String email = emailpas.getText().toString().trim();

        if(email.isEmpty()){
            emailpas.setError("write email");
            emailpas.requestFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailpas.setError("provide a valid email");
            emailpas.requestFocus();
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(forgotPassword.this, "check email", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(forgotPassword.this, "something wrong happened", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}