package com.example.hanieum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HealthCare extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_care);

        Button bt_HospitalReservation = findViewById(R.id.HospitalReservation);
        bt_HospitalReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HospitalReservation.class);
                startActivity(intent);
            }
        });

        Button bt_ReservationCheck = findViewById(R.id.ReservationCheck);
        bt_ReservationCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReservationCheck.class);
                startActivity(intent);
            }
        });

        Button bt_PsychoTherapy = findViewById(R.id.PsychoTherapy);
        bt_PsychoTherapy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PsychoTherapy.class);
                startActivity(intent);
            }
        });

        Button bt_HospitalLocation = findViewById(R.id.HospitalLocation);
        bt_HospitalLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HospitalLocation.class);
                startActivity(intent);
            }
        });
    }
}