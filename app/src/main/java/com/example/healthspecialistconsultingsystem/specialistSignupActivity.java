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

import com.example.healthspecialistconsultingsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class specialistSignupActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private EditText fullName, speciality,healthIdNumber, hospitalOperatingFrom, emailAddress,Password;
    private Button signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_signup);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        mAuth = FirebaseAuth.getInstance();
        fullName= (EditText) findViewById(R.id.fullName);
        speciality=(EditText) findViewById(R.id.speciality);
        healthIdNumber=(EditText) findViewById(R.id.healthIdNumber);
        hospitalOperatingFrom=(EditText) findViewById(R.id.hospitalOperatingFrom);
        emailAddress=(EditText) findViewById(R.id.emailAddress);
        Password=(EditText) findViewById(R.id.Password);

        signUp =(Button) findViewById(R.id.signUp);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signUp:
                signUp();
                break;
            
        }
    }

    private void signUp() {
        String fullname= fullName.getText().toString().trim();
        String specialitY= speciality.getText().toString().trim();
        String healthidnumber= healthIdNumber.getText().toString().trim();
        String hospitaloperatingfrom= hospitalOperatingFrom.getText().toString().trim();
        String email= emailAddress.getText().toString().trim();
        String password= Password.getText().toString().trim();

        if (fullname.isEmpty()){
            fullName.setError("name is required");
            fullName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            emailAddress.setError("email is required");
            emailAddress.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailAddress.setError("please provide valid email");
            emailAddress.requestFocus();
            return;
        }
        if(specialitY.isEmpty()){
            speciality.setError("speciality is empty ");
            speciality.requestFocus();
            return;
        }
        if (healthidnumber.isEmpty()){
            healthIdNumber.setError("fill ur id number");
            healthIdNumber.requestFocus();
            return;
        }
        if (hospitaloperatingfrom.isEmpty()){
            hospitalOperatingFrom.setError("which hospital do u operating in");
            hospitalOperatingFrom.requestFocus();
            return;
        }
        if(password.isEmpty()){
            Password.setError("enter password");
            Password.requestFocus();
            return;
        }
        if (password.length()<6){
            Password.setError("password too short");
            Password.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            userSpecialist userSpecialist= new userSpecialist(fullname,specialitY,healthidnumber,hospitaloperatingfrom,email);
                            FirebaseDatabase.getInstance().getReference("usersSpecialist")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userSpecialist).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(specialistSignupActivity.this, "user has been registered ", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(specialistSignupActivity.this,specialistLoginActivity.class));

                                            }else{
                                                Toast.makeText(specialistSignupActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                        }else{
                            Toast.makeText(specialistSignupActivity.this, "Failed to register", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}