package com.example.mywhatsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context;
    ArrayList<Chat> chatlist;

    public ChatAdapter(Context context, ArrayList<Chat> chatlist) {
        this.context = context;
        this.chatlist = chatlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Chat myChat = chatlist.get(position);
        holder.name.setText(myChat.getName());
        holder.count.setText(myChat.getMessage());
        holder.dp.setImageResource(myChat.getDp());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "want to chat with " + myChat.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return chatlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView dp;
        private TextView name;
        private TextView count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dp = itemView.findViewById(R.id.profilePic);
            name = itemView.findViewById(R.id.textView);
            count = itemView.findViewById(R.id.message);
        }
    }

}
