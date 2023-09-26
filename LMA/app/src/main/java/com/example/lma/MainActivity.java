package com.example.lma;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import com.example.lma.databinding.ActivityMainBinding;
import com.example.lma.entity.Category;
import com.example.lma.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ArrayList<Category> categoryList;
    private ActivityMainBinding activityMainBinding;
    private ClickHandlers clickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandlers = new ClickHandlers();
        activityMainBinding.setClickHandlers(clickHandlers);
    }

    public class ClickHandlers {
        public void onFabClick(View view) {
            Toast.makeText(MainActivity.this, "Hlleo Fab is clicked", Toast.LENGTH_SHORT).show();
        }
    }

}