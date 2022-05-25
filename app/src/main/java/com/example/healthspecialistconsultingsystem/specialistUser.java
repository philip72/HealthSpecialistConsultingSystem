package com.example.healthspecialistconsultingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class specialistUser extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_user);

        Button logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(view->{
            FirebaseAuth.getInstance().signOut();
            Intent intent =new Intent(specialistUser.this, specialistLoginActivity.class);
            startActivity(intent);
        });


    }
}