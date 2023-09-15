package com.example.app7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    Button button;
    Spinner spinner;
    RadioButton rb;
    TimePicker time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = findViewById(R.id.radiogroup);
        spinner = findViewById(R.id.spinner1);
        button = findViewById(R.id.button1);
        String[] courses = {"java", "Springboot", "Android Development", "WebDevelopment"};
        ArrayAdapter adp = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adp);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "coureses selected: " + courses[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

// time picker
        time = findViewById(R.id.mytime);
        time.setIs24HourView(true);
// gettind selecteed time


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currenttime = "current time is " + time.getHour() + ":" + time.getMinute();
                Toast.makeText(MainActivity.this, " " + currenttime, Toast.LENGTH_SHORT).show();

                Intent activityintent = new Intent(getApplicationContext(), Activity2.class);
                startActivity(activityintent);


            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                Toast.makeText(MainActivity.this, "" + rb.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}