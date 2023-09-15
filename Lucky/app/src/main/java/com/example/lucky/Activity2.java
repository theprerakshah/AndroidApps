package com.example.lucky;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Activity2 extends AppCompatActivity {

    TextView text2, text3;
    Button bt2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        bt2 = findViewById(R.id.button2);
        Intent i = getIntent();
        String name = i.getStringExtra("name");
        Random random = new Random();
        int random1 = random.nextInt(100);
        text3.setText(name + " your lucky number is " + random1);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData(random1, name);
            }
        });


    }

    private void sendData(int random, String name) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "hey i have got lucky today and my name is " + name);
        intent.putExtra(Intent.EXTRA_SUBJECT, random + " is my lucky number");
        intent.putExtra(Intent.ACTION_CALL,576878900);

        startActivity(Intent.createChooser(intent, "choose where to send this data"));
    }
}