package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Activity2 extends AppCompatActivity {

    // Widgets
    Button play_btn, pause_btn, forward_btn, back_btn;
    TextView time, song_name, title;
    SeekBar seekbar;
    ArrayList<File> songList;
    //Media Player
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    double startTime = 0;
    double finalTime = 0;
    double forwardTime = 5000;
    double backwardTime = 5000;
    static int onetimeonly = 0;
    int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play_btn = findViewById(R.id.play_button);
        pause_btn = findViewById(R.id.pause_button);
        forward_btn = findViewById(R.id.forwad_button);
        back_btn = findViewById(R.id.backward_button);
        song_name = findViewById(R.id.music_name);
        time = findViewById(R.id.song_time);
        seekbar = findViewById(R.id.music_seekbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        songList = (ArrayList) bundle.getParcelableArrayList("songList");
        String songName = intent.getStringExtra("SongName");
        position = intent.getIntExtra("position", 0);
        Uri uri = Uri.parse(songList.get(position).toString());
        mediaPlayer = MediaPlayer.create(this, uri);
        seekbar.setClickable(false);
//        song_name.setText(getResources().getIdentifier("music", "raw", getPackageName()));
        song_name.setText(songName);

        play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }

        });
        pause_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();

            }
        });
        forward_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;
                if ((temp + forwardTime) <= finalTime) {
                    startTime += forwardTime;
                    mediaPlayer.seekTo((int) startTime);
                } else {
                    Toast.makeText(Activity2.this, "song is forward over MF!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = (int) startTime;
                if ((startTime - backwardTime) > 0) {
                    startTime -= backwardTime;
                    mediaPlayer.seekTo((int) startTime);
                } else {
                    Toast.makeText(Activity2.this, "song is backward over MF!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void playMusic() {
        mediaPlayer.start();
        startTime = mediaPlayer.getCurrentPosition();
        finalTime = mediaPlayer.getDuration();
        if (onetimeonly == 0) {
            seekbar.setMax((int) finalTime);
            onetimeonly = 1;
        }
        time.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) finalTime), TimeUnit.MILLISECONDS.toSeconds((long) finalTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) finalTime))));
        seekbar.setProgress((int) startTime);
        handler.postDelayed(updateSongTime, 100);
    }

    private Runnable updateSongTime = new Runnable() {
        @Override
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            time.setText(String.format("%d min, %d sec", TimeUnit.MILLISECONDS.toMinutes((long) startTime), TimeUnit.MILLISECONDS.toSeconds((long) startTime) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) startTime))));
            seekbar.setProgress((int) startTime);
            handler.postDelayed(this, 100);
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.pause();
    }
}