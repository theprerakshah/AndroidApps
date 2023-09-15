package com.example.tablayout;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout tab = findViewById(R.id.tabLayout2);
        TabItem chatTab = findViewById(R.id.chatsview);
        TabItem statusTab = findViewById(R.id.statusview);
        TabItem callTab = findViewById(R.id.chatsview);
        ViewPager viewPager = findViewById(R.id.viewpager);

        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(), tab.getTabCount());
        viewPager.setAdapter(myAdapter);
        tab.setupWithViewPager(viewPager);

    }
}