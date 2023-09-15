package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText et;
    Button myBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.myname);
        et = (EditText) findViewById(R.id.myedittext);
        String textwegot = (String) et.getText().toString();
        System.out.println("het!!!! text is here" + textwegot);
        myBt = findViewById(R.id.button1);

        // handling the click button
        myBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
// we can sepeicfie action when the button is clicked
                Toast.makeText(MainActivity.this, et.getText(), Toast.LENGTH_SHORT).show();


            }
        });

    }
}