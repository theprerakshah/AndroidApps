package com.example.database;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        displaySavedText();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                saveAndDisplayText(text);

            }
        });

    }

    private void displaySavedText() {
        //Retrieving the values from SharedPref
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPref", MODE_PRIVATE);

        textView.setText(sharedPreferences.getString("name", " "));


    }

    // below method is used to save and display text in current session
    private void saveAndDisplayText(String text) {

        // Display the text
        textView.setText(text);

        // Saving the Text into shared prefrernce
        SharedPreferences sharedPreferences = getSharedPreferences("mySharedPref", MODE_PRIVATE);


        // Writing  data to shared pref
        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString("name", text);
        edit.commit();


    }
}