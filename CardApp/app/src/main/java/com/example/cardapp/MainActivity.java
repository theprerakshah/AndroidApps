package com.example.cardapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<Game> mygamelist = new ArrayList<>();
    int[] icon = {R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4, R.drawable.card5, R.drawable.card6, R.drawable.card1, R.drawable.card2, R.drawable.card3, R.drawable.card4, R.drawable.card5, R.drawable.card6, R.drawable.card1, R.drawable.card2, R.drawable.card3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleview);
        dataFiller();
        adapter = new MyAdapter(mygamelist, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void dataFiller() {
        String[] name = getResources().getStringArray(R.array.gameName);
        String[] downloads = getResources().getStringArray(R.array.gameDownloads);
        for (int i = 0; i < name.length; i++) {
            mygamelist.add(new Game(name[i], icon[i], "Downloads: "+downloads[i]));
        }
    }
}