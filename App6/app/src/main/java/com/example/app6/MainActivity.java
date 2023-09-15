package com.example.app6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    Button bt1;
    CheckBox ch1, ch2, ch3;
    RadioButton rb1, rb2, rb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ch1 = findViewById(R.id.checkbox1);
        ch2 = findViewById(R.id.checkbox2);
        ch3 = findViewById(R.id.checkbox3);
        bt1 = findViewById(R.id.button1);
        rb1 = findViewById(R.id.radio1);
        rb2 = findViewById(R.id.radio2);
        rb3 = findViewById(R.id.radio3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topings = " chees";

                if (ch1.isChecked()) {
                    topings += ", onion";
                }
                if (ch2.isChecked()) {
                    topings += ", tomato";
                }
                if (ch3.isChecked()) {
                    topings += ", jalpino";
                }
                String drink="";
                if (rb1.isChecked()) {
                    drink += ", coke";
                }
                if (rb2.isChecked()) {
                    drink += ", Lemonade";
                }
                if (rb3.isChecked()) {
                    drink += ", Mirinda";
                }
                Toast.makeText(getApplicationContext(), "Topings:" + topings+"\n Soft Drink:"+drink, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;
    }
}