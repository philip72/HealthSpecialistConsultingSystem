package com.example.healthspecialistconsultingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        Button specialistButton = (Button) findViewById(R.id.specialistButton);
        specialistButton.setOnClickListener(view->{
           Intent intent = new Intent(MainActivity.this, specialistLoginActivity.class);
           startActivity(intent);
       });
    }


}