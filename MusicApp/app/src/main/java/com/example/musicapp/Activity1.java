package com.example.musicapp;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.READ_MEDIA_AUDIO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Activity1 extends AppCompatActivity {
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        myListView = findViewById(R.id.mylist);
//        Dexter.withContext(this).withPermission(READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
//            @Override
//            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                displaySongs();
//            }
//
//            @Override
//            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                Toast.makeText(Activity1.this, "Permission has been denied!!", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                permissionToken.continuePermissionRequest();
//            }
//        }).check();

        Dexter.withContext(this).withPermissions(READ_EXTERNAL_STORAGE, READ_MEDIA_AUDIO).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                displaySongs();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                Toast.makeText(Activity1.this, "Permission has been denied!!", Toast.LENGTH_SHORT).show();
            }
        }).check();

    }

    private void displaySongs() {
//        String DIRECTORY_MUSIC = "Download";
        ArrayList<File> songList = findSong(Environment.getExternalStorageDirectory());
        ArrayList<String> songName = new ArrayList<>();
        for (File song : songList) {
            songName.add(song.getName().toLowerCase().replace(".mp3", "").replace(".wav", ""));
        }
        ArrayAdapter<String> songAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songName);
        myListView.setAdapter(songAdapter);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Activity1.this, Activity2.class);
                intent.putExtra("position", position);
                intent.putExtra("songList", songList);
                intent.putExtra("position", songName.get(position));
                startActivity(intent);
            }
        });

    }

    private ArrayList<File> findSong(File externalStorageDirectory) {
        ArrayList<File> songList = new ArrayList<File>();
        File[] file = externalStorageDirectory.listFiles();
        if (file != null) {
            for (File myfile : file) {
                if (myfile.isDirectory()) {
                    songList.addAll(findSong(myfile));
                } else {
                    if (myfile.getName().toString().toLowerCase().endsWith(".mp3") || myfile.getName().toString().toLowerCase().endsWith(".wav") && !myfile.getName().toString().startsWith(".")) {
                        songList.add(myfile);
                    }
                }
            }
        } else {
            Toast.makeText(this, "there is nothing in External Storage", Toast.LENGTH_SHORT).show();
        }


        return songList;
    }
}