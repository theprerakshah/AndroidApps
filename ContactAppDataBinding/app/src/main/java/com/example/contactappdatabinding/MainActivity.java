package com.example.contactappdatabinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import com.example.contactappdatabinding.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {


    private ContactAppDatabase contactAppDatabase;
    private ArrayList<Contact> contactArrayList = new ArrayList<>();
    private ContactDataAdapter contactAdapter;
    //Binding
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickEventHandler clickHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        clickHandler = new MainActivityClickEventHandler(this);
        activityMainBinding.setClickHandler(clickHandler);
        //RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        //Adapter
        contactAdapter = new ContactDataAdapter(contactArrayList);
        recyclerView.setAdapter(contactAdapter);
        //DataBase
        contactAppDatabase = Room.databaseBuilder(
                getApplicationContext(), ContactAppDatabase.class, "ContactDB"
        ).build();
        // Add all Data in db
        LoadData();
        //Handling swiping events
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contact contact = contactArrayList.get(viewHolder.getAdapterPosition());
                Log.d(TAG, "Entered into onSwiped functions: ");
                deleteContact(contact);
            }
        }).attachToRecyclerView(recyclerView);
        // with FloatingActionButton

//        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, AddNewContactActivity.class);
//                startActivityForResult(intent, 1);
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK) {
            String name = data.getStringExtra("NAME");
            String email = data.getStringExtra("EMAIL");

            Contact contact = new Contact(name, email);
            addNewContact(contact);

        }
    }
    private void deleteContact(Contact contact) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler1 = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //onBackground
                contactAppDatabase.getContactDao().delete(contact);
                contactArrayList.remove(contact);
                //on Post Execution
                handler1.post(new Runnable() {
                    @Override
                    public void run() {
                        contactAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }
    private void addNewContact(Contact contact) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler1 = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //onBackground
                contactAppDatabase.getContactDao().insert(contact);
                contactArrayList.add(contact);
                //on Post Execution
                handler1.post(new Runnable() {
                    @Override
                    public void run() {
                        contactAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }

    private void LoadData() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler1 = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //onBackground
                contactArrayList.addAll(
                        contactAppDatabase.getContactDao().getAllContact()
                );
                //on Post Execution
                handler1.post(new Runnable() {
                    @Override
                    public void run() {
                        contactAdapter.setContacts(contactArrayList);
                        contactAdapter.notifyDataSetChanged();
                    }
                });
            }
        });


    }

    public class MainActivityClickEventHandler {

        Context context;

        public MainActivityClickEventHandler(Context context) {
            this.context = context;
        }

        public void onFabClicked(View view) {
            Intent intent = new Intent(MainActivity.this, AddNewContactActivity.class);
            startActivityForResult(intent, 1);


        }

    }

}