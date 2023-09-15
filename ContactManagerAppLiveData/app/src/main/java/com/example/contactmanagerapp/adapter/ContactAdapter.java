package com.example.contactmanagerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactmanagerapp.MainActivity;
import com.example.contactmanagerapp.R;
import com.example.contactmanagerapp.database.entity.Contact;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Contact> contactList;
    private MainActivity mainActivity;

    public ContactAdapter(Context context, ArrayList<Contact> contactList, MainActivity mainActivity) {
        this.context = context;
        this.contactList = contactList;
        this.mainActivity = mainActivity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.name = itemView.findViewById(R.id.name);
            this.email = itemView.findViewById(R.id.email);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Contact contact = contactList.get(position);
        holder.email.setText(contact.getEmail());
        holder.name.setText(contact.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.addAndEditContacts(true, contact, contactList.indexOf(contact));
            }
        });


    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    // viewHolder Class


}
