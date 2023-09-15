package com.example.counterviewmodelapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        // setting data in view in normal way
//        textView.setText("You Clicked me:" + mainActivityViewModel.oneTimeOfInitial() + "times");

        //Using live data to get the counter
        LiveData<Integer> liveCount = mainActivityViewModel.oneTimeOfInitial();

        liveCount.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                textView.setText("you Clicked " + integer + " times");
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.onIncreament();
//                textView.setText("You click " + mainActivityViewModel.onIncreament() + " Times");
            }
        });
    }
}