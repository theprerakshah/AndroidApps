package com.example.contactmanagerapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
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
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactmanagerapp.adapter.ContactAdapter;
import com.example.contactmanagerapp.database.DatabaseHelper;
import com.example.contactmanagerapp.database.entity.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MY Favourite Contacts");


        //Recycler view
        recyclerView = findViewById(R.id.recycler_view_contacts);
        db = new DatabaseHelper(this);

        // Contact list
        contactList.addAll(db.getAllContact());

        contactAdapter = new ContactAdapter(this, contactList, MainActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(contactAdapter);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAndEditContacts(false, null, -1);
            }
        });


    }

    public void addAndEditContacts(final boolean wantToUpdate, final Contact contact, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
        View view = layoutInflater.inflate(R.layout.layout_add_contact, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setView(view);

        TextView Title = view.findViewById(R.id.new_contact_title);
        final EditText contactName = view.findViewById(R.id.name);
        final EditText contactEmail = view.findViewById(R.id.email);

        Title.setText(!wantToUpdate ? "Add New Contact" : "Edit Contact");
        if (wantToUpdate && contact != null) {
            contactName.setText(contact.getName());
            contactEmail.setText(contact.getEmail());
        }
        alertDialogBuilder.setCancelable(false).setPositiveButton(wantToUpdate ? "Update" : "Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (wantToUpdate) {
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
                if (TextUtils.isEmpty(contactName.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter a name", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                if (wantToUpdate && contact != null) {
                    updateContact(contactName.getText().toString(), contactEmail.getText().toString(), position);
                } else {
                    createContact(contactName.getText().toString(), contactEmail.getText().toString());
                }
            }
        });


    }

    private void updateContact(String name, String email, int position) {
        Contact contact = contactList.get(position);
        contact.setName(name);
        contact.setEmail(email);
        db.updateRowTable(contact);
        contactList.set(position, contact);
        contactAdapter.notifyDataSetChanged();

    }

    private void createContact(String name, String email) {
        long id = db.insertIntoTable(name, email);
        Contact contact = db.getContact(id);
        if (contact != null) {
            contactList.add(0, contact);
            contactAdapter.notifyDataSetChanged();
        }

    }

    private void deleteContact(Contact contact, int position) {
        contactList.remove(position);
        db.deleteRowTable(contact);
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
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