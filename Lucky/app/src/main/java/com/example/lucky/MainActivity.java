package com.example.lucky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView test1;
    Button bt1;
    EditText etext1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test1 = findViewById(R.id.text1);
        etext1 = (EditText) findViewById(R.id.editText1);
        bt1 = findViewById(R.id.button1);
        Resources res = getResources();
        int myColor = res.getColor(R.color.purple);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = etext1.getText().toString();
                //Explicit intent
                Intent intent = new Intent(getApplicationContext(), Activity2.class);
                intent.putExtra("name", name1);
                startActivity(intent);
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