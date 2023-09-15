package com.example.frenchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.myphoto);
//        Glide.with(this).load(R.drawable.himalaya).into(image);
        image.setImageResource(R.drawable.himalaya);

    }

    public void speakColor(View view) {
        Button clickButtion = (Button) view;
        MediaPlayer mp = MediaPlayer.create(this, getResources().getIdentifier(clickButtion.getTag().toString(), "raw", getPackageName()));
        mp.start();
    }
}