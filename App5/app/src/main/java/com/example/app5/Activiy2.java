package com.example.app5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;

public class Activiy2 extends AppCompatActivity {
    TextView txt2, txt3;
    Button bt2;
    ImageButton imageBt;
    EditText search1;
    String question = "https://www.google.com/search?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiy2);
        txt2 = findViewById(R.id.text2);
        bt2 = findViewById(R.id.button2);
        imageBt = findViewById(R.id.imageButton);
        txt3 = findViewById(R.id.text3);


        String myquestion = question;
        imageBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search1 = (EditText) findViewById(R.id.search12);
                String ques[] = search1.getText().toString().split(" ");
                question += ques[0];
                for (int i = 1; i < ques.length; i++) {

                    question = question + "+" + ques[i];
                }
                Toast.makeText(Activiy2.this, question, Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(Intent.ACTION_VIEW);
                intent3.setData(Uri.parse(question));
                startActivity(intent3);
            }
        });


        Intent intentReciver = getIntent();
        int x = intentReciver.getIntExtra("x", 0);
        int y = intentReciver.getIntExtra("y", 0);
        int sum = x + y;
        txt2.setText(txt2.getText().toString() + " \n \n    sum is " + sum);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(inte2);


            }
        });
    }
}