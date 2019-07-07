package com.example.hanieum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Communication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication);

        Button bt_VoiceCall = findViewById(R.id.VoiceCall);
        bt_VoiceCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoiceCall.class);
                startActivity(intent);
            }
        });

        Button bt_VideoCall = findViewById(R.id.VideoCall);
        bt_VideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoCall.class);
                startActivity(intent);
            }
        });
        Button bt_Message = findViewById(R.id.Message);
        bt_Message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Message.class);
                startActivity(intent);
            }
        });
        Button bt_Cloud = findViewById(R.id.Cloud);
        bt_Cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Cloud.class);
                startActivity(intent);
            }
        });
    }
}
