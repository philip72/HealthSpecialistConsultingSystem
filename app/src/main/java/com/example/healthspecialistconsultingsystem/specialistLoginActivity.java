package com.example.healthspecialistconsultingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class specialistLoginActivity extends AppCompatActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_login);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(view->{
            Intent intent = new Intent(specialistLoginActivity.this, specialistSignupActivity.class);
            startActivity(intent);
        });
    }





}