package com.example.vocals;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.songs);
        Dexter.withContext(this)
                .withPermission(READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        displaySong();

                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();


    }
    public ArrayList<File>findSong(File file){
        ArrayList<File>arraylist=new ArrayList<>();
        File[] files= file.listFiles();
        if(files!=null){
            for(File singleFile:files){
                if(singleFile.isDirectory() && !singleFile.isHidden()){
                    arraylist.addAll(findSong(singleFile));
                }
                else{
                    if(singleFile.getName().toString().endsWith(".mp3") || singleFile.getName().toString().endsWith("wav" )&& !singleFile.getName().toString().startsWith(".") )
                    {
                        arraylist.add(singleFile);
                    }
                }
            }
        }
        else{
            Log.d("info", "findSong: No files found to play");
        }
        return  arraylist;
    }
    public void displaySong() {
        ArrayList<File>mySongs=findSong(Environment.getExternalStorageDirectory());
        ArrayList<String>items=new ArrayList<>();
        for(int i=0;i<mySongs.size();i++){
            items.add(mySongs.get(i).getName().replace(".mp3","").replace(".wav",""));
        }

//        CustomAdapter songAdapter=new CustomAdapter(this,items);
        ArrayAdapter<String> songAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(songAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this,PlaySong.class);
                intent.putExtra("position",i);
                intent.putExtra("songlist",mySongs);
                intent.putExtra("songname",items.get(i));
                startActivity(intent);
            }


        });

    }

}