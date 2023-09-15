package com.example.mywhatsapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout myTab = findViewById(R.id.tabLayout2);
        TabItem chat = findViewById(R.id.chatView);
        TabItem status = findViewById(R.id.statusView);
        TabItem call = findViewById(R.id.callView);
        TextView text = findViewById(R.id.textview);
        ViewPager viewPager = findViewById(R.id.viewPager);
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(), myTab.getTabCount());
        viewPager.setAdapter(myAdapter);
        myTab.setupWithViewPager(viewPager);


    }
}