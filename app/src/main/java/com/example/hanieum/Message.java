package com.example.hanieum;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Button bt_ChildrenMessage = findViewById(R.id.ChildrenMessage);
        bt_ChildrenMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageDetails.class);
                startActivity(intent);
            }
        });

        Button bt_GrandChildrenMessage = findViewById(R.id.GrandChildrenMessage);
        bt_GrandChildrenMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageDetails.class);
                startActivity(intent);
            }
        });

        Button bt_KindredMessage = findViewById(R.id.KindredMessage);
        bt_KindredMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageDetails.class);
                startActivity(intent);
            }
        });

        Button bt_FriendMessage = findViewById(R.id.FriendMessage);
        bt_FriendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MessageDetails.class);
                startActivity(intent);
            }
        });
    }
}
