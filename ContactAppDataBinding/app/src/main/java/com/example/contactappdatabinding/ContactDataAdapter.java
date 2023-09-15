package com.example.contactappdatabinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.example.contactappdatabinding.databinding.ContactListBinding;

import java.util.ArrayList;

public class ContactDataAdapter extends RecyclerView.Adapter<ContactDataAdapter.myViewHolder> {


    private ArrayList<Contact> contactList;

    public ContactDataAdapter() {
    }

    public ContactDataAdapter(ArrayList<Contact> contactList) {
        this.contactList = contactList;

    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list, parent, false);
//        return new myViewHolder(view);
//        ContactListBinding
        ContactListBinding contactListBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.contact_list, parent, false
        );

        return new myViewHolder(contactListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        Contact contact = contactList.get(position);
//        holder.name.setText(contact.getName());
//        holder.email.setText(contact.getEmail());
        holder.contactListBinding.setContact(contact);


    }

    @Override
    public int getItemCount() {

        if (contactList != null) {
            return contactList.size();

        } else {
            return 0;
        }
    }

    public void setContacts(ArrayList<Contact> contactList) {
        this.contactList = contactList;
        notifyDataSetChanged();
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        private ContactListBinding contactListBinding;
        //        private TextView name;
//        private TextView email;

        public myViewHolder(@NonNull ContactListBinding contactListBinding) {
            super(contactListBinding.getRoot());
//            this.name = itemView.findViewById(R.id.name);
//            this.email = itemView.findViewById(R.id.email);
            this.contactListBinding = contactListBinding;

        }
    }

}
