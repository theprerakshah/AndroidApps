package com.example.app7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TimePicker;

import java.sql.Time;

public class Activity2 extends AppCompatActivity {
    Button bt, bt2, bt3;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        bt = findViewById(R.id.timesubmit);
        bt2 = findViewById(R.id.date);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setProgress(20);
        bt3 = findViewById(R.id.addprogress);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.incrementProgressBy(10);
//                progressBar.setProgress(0);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getSupportFragmentManager(), "pick time");

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datepicker = new DatePickerFragment();
                datepicker.show(getSupportFragmentManager(), "pick a date");

            }
        });

    }
}