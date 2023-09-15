package com.example.databinding;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Person person1 = new Person("Jack", "Jack@gmail.com");

        //TextView binding
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setPerson(person1);
        Person person = activityMainBinding.getPerson();

        //Event handler binding
        clickHandler = new MainActivityClickHandler(this);
        activityMainBinding.setClickHandler(clickHandler);

    }

    public class MainActivityClickHandler {
        Context context;

        public MainActivityClickHandler(Context context) {
            this.context = context;
        }


        public void clickHandlerButton1(View view) {
            Toast.makeText(context, "Buttton has been clicked", Toast.LENGTH_SHORT).show();
        }

        public void clickHandlerButton2(View view) {
            Toast.makeText(context, "Button 2 has been clicked", Toast.LENGTH_SHORT).show();
        }


    }


}