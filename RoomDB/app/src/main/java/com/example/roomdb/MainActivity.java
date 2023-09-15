package com.example.roomdb;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.example.roomdb.adapter.ContactAdapter;
import com.example.roomdb.database.AppDatabase;
import com.example.roomdb.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("My favourite Contacts");

        recyclerView = findViewById(R.id.recycler_view_contacts);

        //CallBack methods
        RoomDatabase.Callback callback = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Log.i("myTAG", "onCreate: Database is just created");
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);

                Log.i("myTag", "Database is opened: ");
            }
        };

        // Database
        appDatabase = Room.databaseBuilder(
                getApplicationContext(), AppDatabase.class, "ContactDB"
        ).addCallback(callback).build();

// this is time consuming for big data and so we use executor class
//        contactList.addAll(appDatabase.getContactDao().getAllContact());
        getAllContactInBackground();

        contactAdapter = new ContactAdapter(this, contactList, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAndEdit(false, null, -1);
            }
        });

    }


    public void addAndEdit(final boolean isUpdated, final Contact contact, final int position) {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_add_contact, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(view);

        TextView contatctTitle = view.findViewById(R.id.new_contact_title);
        final EditText name = view.findViewById(R.id.name);
        final EditText email = view.findViewById(R.id.email);

        contatctTitle.setText(!isUpdated ? "Add new Contact" : "Update Contact");
        if (isUpdated && contact != null) {
            name.setText(contact.getName());
            email.setText(contact.getEmail());
        }

        alertDialogBuilder.setCancelable(false).setPositiveButton(isUpdated ? "Update" : "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (isUpdated) {
                    deleteContact(contact, position);
                } else {
                    dialog.cancel();
                }
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();

                } else {
                    alertDialog.dismiss();
                }

                if (isUpdated && contact != null) {
                    updateContact(name.getText().toString(), email.getText().toString(), position);
                } else {
                    createContact(name.getText().toString(), email.getText().toString());
                }

            }
        });


    }

    private void getAllContactInBackground() {


        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {


                // in background this below task is completed and seperate thread
                contactList.addAll(appDatabase.getContactDao().getAllContact());

                //After data is load using handler we run the runnalble code
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        contactAdapter.notifyDataSetChanged();
                    }
                });

            }
        });

    }

    private void createContact(String name, String email) {

        long id = appDatabase.getContactDao().addContact(new Contact(name, email, 0));
        Contact contact = appDatabase.getContactDao().getContact(id);
        if (contact != null) {

            contactList.add(contact);
            contactAdapter.notifyDataSetChanged();
        }

    }

    private void updateContact(String name, String email, int position) {

        Contact contact = contactList.get(position);
        contact.setName(name);
        contact.setEmail(email);
        appDatabase.getContactDao().updateContact(contact);
        contactAdapter.notifyDataSetChanged();
    }

    private void deleteContact(Contact contact, int position) {
        contactList.remove(position);
        appDatabase.getContactDao().deleteContact(contact);
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}