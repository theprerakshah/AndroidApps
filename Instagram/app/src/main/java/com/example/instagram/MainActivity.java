package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    ListView mylist;
    Insta myInstagram;
    ArrayList<Insta> instaArrayList = new ArrayList<>();
    String[] ids = {"mike", "bike", "rike", "nike", "fike", "dike", "sike", "zike"};
    String count = "0";
    int[] images = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5, R.drawable.pic6, R.drawable.pic7, R.drawable.pic8};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.icon);
        mylist = findViewById(R.id.listview);
        logo.setImageResource(R.drawable.loga);
        Random rand = new Random();
        for (int i = 0; i < ids.length; i++) {
            Insta insta = new Insta(ids[i], count, randomString(), images[i], R.drawable.like, R.drawable.share);
            instaArrayList.add(insta);

        }
        MyAdapter adapter = new MyAdapter(instaArrayList, getApplicationContext());
        mylist.setAdapter(adapter);
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int count = (Integer.parseInt(adapter.getItem(position).getCount()));
                ++count;
                Toast.makeText(MainActivity.this, "Like " + count, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 20;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        return generatedString;
    }
}