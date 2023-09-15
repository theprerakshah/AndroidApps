package com.example.app5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button bt;
    TextView textview;
    EditText editX;
    EditText editY;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreateMethodIsCalled()", Toast.LENGTH_SHORT).show();
        bt = findViewById(R.id.button);
        textview = findViewById(R.id.text1);
        editY = findViewById(R.id.yedit);
        editX = findViewById(R.id.xedit);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Explicit Intent
                Intent inte1 = new Intent(getApplicationContext(), Activiy2.class);
                // Send Data Beteween Acitivities
                inte1.putExtra("x", Integer.parseInt(editX.getText().toString()));
                inte1.putExtra("y", Integer.parseInt(editY.getText().toString()));
                startActivity(inte1);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Toast.makeText(this, "onStartMethodIsCalled()", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Toast.makeText(this, "onResumeMethodIsCalled()", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Toast.makeText(this, "onRestartMethodIsCalled()", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Toast.makeText(this, "onStopMethodIsCalled()", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Toast.makeText(this, "onPauseMethodIsCalled()", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Toast.makeText(this, "onDestroyMethodIsCalled()", Toast.LENGTH_SHORT).show();
//    }
}