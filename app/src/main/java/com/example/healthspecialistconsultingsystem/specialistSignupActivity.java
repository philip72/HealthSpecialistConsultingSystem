package com.example.healthspecialistconsultingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthspecialistconsultingsystem.R;

public class specialistSignupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_signup);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

    }
}