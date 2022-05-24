package com.example.healthspecialistconsultingsystem;

public class userSpecialist {
    public String fullName, speciality, healthIdNo,hospfrom, email;
    public userSpecialist(){

    }
    public userSpecialist(String fullName,String speciality,String healthIdNo, String hospfrom, String email){
        this.fullName= fullName;
        this.speciality = speciality;

        this.healthIdNo = healthIdNo;
        this.hospfrom = hospfrom;
        this.email = email;
    }
}
