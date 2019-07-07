package com.example.hanieum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hanieum.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bt_Communication = findViewById(R.id.Communication);
        bt_Communication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Communication.class);
                startActivity(intent);
            }
        });

        Button bt_HealthCare = findViewById(R.id.HealthCare);
        bt_HealthCare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HealthCare.class);
                startActivity(intent);
            }
        });
    }
}
