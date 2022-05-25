package com.example.healthspecialistconsultingsystem;

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

public class specialistLoginActivity extends AppCompatActivity  {

    private FirebaseAuth mAuth;
    private EditText password;
    private EditText emailAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        mAuth= FirebaseAuth.getInstance();


        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(view->{
            Intent intent = new Intent(specialistLoginActivity.this, specialistSignupActivity.class);
            startActivity(intent);
        });
        TextView forgotPassword = (TextView)findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(view->{
            Intent intent  = new Intent(specialistLoginActivity.this, forgotPassword.class);
            startActivity(intent);
        });
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        password = (EditText) findViewById(R.id.password);
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(view->{
            userLogin();
        });

    }

    private void userLogin() {
        String email = emailAddress.getText().toString().trim();
        String Password = password.getText().toString().trim();


        if(email.isEmpty()){
            emailAddress.setError("enter email");
            emailAddress.requestFocus();
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailAddress.setError("enter a valid email");
            emailAddress.requestFocus();
        }
        if (Password.isEmpty()){
            password.setError("password is empty ");
            password.requestFocus();
        }
        mAuth.signInWithEmailAndPassword(email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (firebaseUser.isEmailVerified()){
                            //specialist profile
                    startActivity(new Intent(specialistLoginActivity.this,specialistUser.class));}
                    else {
                        firebaseUser.sendEmailVerification();
                        Toast.makeText(specialistLoginActivity.this,"check email to verify",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(specialistLoginActivity.this, "check your credentials ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


}