package com.example.app3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView appName, counter;
    Button button, reset;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appName = findViewById(R.id.name);
        counter = findViewById(R.id.counter);
        button = findViewById(R.id.button);
        reset = findViewById(R.id.reset);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.setText("" + counterincrease());
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter.setText("" + 0);
                count = 0;
            }
        });
    }

    public int counterincrease() {
        return ++count;
    }
}