package com.example.contactappdatabinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.contactappdatabinding.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {


    private ActivityAddNewContactBinding activityAddNewContactBinding;
    Contact contact;
    private AddNewContactClickHandler addClickHandlers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);

        contact = new Contact();
        activityAddNewContactBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_contact);
        activityAddNewContactBinding.setContact(contact);
        addClickHandlers = new AddNewContactClickHandler(this);
        activityAddNewContactBinding.setClickHandler(addClickHandlers);
    }


    public class AddNewContactClickHandler {

        Context context;

        public AddNewContactClickHandler(Context context) {
            this.context = context;
        }


        public void addContactOnSubmit(View view) {
            if (contact.getName() == null) {
                Toast.makeText(context, "Fields cannot be empty!!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent();
                intent.putExtra("NAME", contact.getName());
                intent.putExtra("EMAIL", contact.getEmail());
                setResult(RESULT_OK, intent);
                finish();
            }

        }
    }
}