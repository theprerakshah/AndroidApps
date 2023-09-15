package com.example.vaccine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    //    String vaccinName[] = {"vaccine1", "vaccine2", "vaccine3", "vaccine4", "vaccine5", "vaccine6", "vaccine7"};
    int vaccineIcon[] = {R.drawable.vaccine1, R.drawable.vaccine4, R.drawable.vaccine5, R.drawable.vaccine6, R.drawable.vaccine7, R.drawable.vaccine8, R.drawable.vaccine9, R.drawable.vaccine1, R.drawable.vaccine4, R.drawable.vaccine5, R.drawable.vaccine6, R.drawable.vaccine7, R.drawable.vaccine8, R.drawable.vaccine9};
    myAdapter adapter;
    ArrayList<Vaccine> myVaccineList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle);
        setUpVaccineModel();
        adapter = new myAdapter(this, myVaccineList);
        recyclerView.setHasFixedSize(true);


        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void setUpVaccineModel() {
        String[] vaccineName = getResources().getStringArray(R.array.vaccine_name);
        String[] vaccinePrice = getResources().getStringArray(R.array.vaccine_price);
        for (int i = 0; i < vaccineName.length; i++) {
            myVaccineList.add(new Vaccine(vaccineName[i], vaccineIcon[i], vaccinePrice[i]));
        }
    }

}