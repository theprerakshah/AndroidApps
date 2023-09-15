package com.example.worldcup;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.lights.LightsManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mylistview;
    private static MyAdapter adapter;
    //    String countries[] = {"India", "USA", "Japan", "Australia", "Canada", "Germany", "Spain", "Russia"};
    ArrayList<Country> myCountryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylistview = findViewById(R.id.listview);
//        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.mylayout, R.id.mylayout, countries);
//        mylistview.setAdapter(adapter);
        myCountryList = new ArrayList<>();
        myCountryList.add(new Country("Germany", "2", R.drawable.germany));
        myCountryList.add(new Country("Brazil", "4", R.drawable.brazil));
        myCountryList.add(new Country("France", "3", R.drawable.france));
        myCountryList.add(new Country("SaudiArabia", "5", R.drawable.saudiarabia));
        myCountryList.add(new Country("Spain", "6", R.drawable.spain));
        myCountryList.add(new Country("United Kingdom", "1", R.drawable.unitedkingdom));
        myCountryList.add(new Country("United States", "2", R.drawable.unitedstates));
        adapter = new MyAdapter(myCountryList, getApplicationContext());
        mylistview.setAdapter(adapter);
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "country: " + adapter.getItem(position).getCountryName() + " NO. Wins " + adapter.getItem(position).getWin(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}