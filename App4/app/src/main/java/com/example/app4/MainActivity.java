package com.example.app4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button submit;
    EditText input;
    Switch switch1;
    TextView output;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submit);
        input = findViewById(R.id.userinput);
        switch1 = findViewById(R.id.switch1);
        output = findViewById(R.id.answer);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                output.setText("" + convertweight(Double.parseDouble(input.getText().toString())));
            }
        });
    }

    public Double convertweight(Double kg) {
        return kg * 2;
    }
}