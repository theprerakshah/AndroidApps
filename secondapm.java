package com.example.vocals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PlaySong extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
        updateSeek.interrupt();
    }

    TextView txtsong;
    ImageView next,previous,pause;
    MediaPlayer mp=new MediaPlayer();
    Thread updateSeek;
    int current_position=0;
    int position;
    ArrayList<File> songs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        txtsong=findViewById(R.id.txtsong);
        next=findViewById(R.id.next);
        previous=findViewById(R.id.previous);
        pause=findViewById(R.id.pause);
        SeekBar seekBar=findViewById(R.id.seekBar);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        
        songs= (ArrayList) bundle.getParcelableArrayList("songlist");
       String txtcontent= intent.getStringExtra("songname");
       txtsong.setText(txtcontent);
       txtsong.setSelected(true);
       position=intent.getIntExtra("position",0);
       Uri uri=Uri.parse(songs.get(position).toString());// to string works but get name does not question why this happened?
       mp=MediaPlayer.create(this,uri);
       mp.start();
       seekBar.setMax(mp.getDuration());
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
              mp.seekTo(seekBar.getProgress());
            }
        });
        updateSeek=new Thread(){
            @Override
            public void run() {

                super.run();
                try{
                    while(current_position<mp.getDuration()){
                        current_position=mp.getCurrentPosition();
                        seekBar.setProgress(current_position);
                        sleep(800);
                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }
        };
        updateSeek.start();
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                mp.release();
                if(position!=0){
                    position=position-1;
                }
                else{
                    position=songs.size()-1;
                }
                txtsong.setText(songs.get(position).toString().replace(".mp3",""));
                Uri uri=Uri.parse(songs.get(position).toString());
                mp= MediaPlayer.create(getApplicationContext(),uri);
                mp.start();
                pause.setImageResource(R.drawable.pause);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    pause.setImageResource(R.drawable.play);
                }
                else{
                    mp.start();
                    pause.setImageResource(R.drawable.pause);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.stop();
                mp.release();
                if(position!=songs.size()-1){
                    position=position+1;
                }
                else{
                    position=0;
                }
                txtsong.setText(songs.get(position).toString().replace(".mp3",""));
                Uri uri=Uri.parse(songs.get(position).toString());
                mp= MediaPlayer.create(getApplicationContext(),uri);
                mp.start();
                pause.setImageResource(R.drawable.pause);
            }
        });

    }
}