package com.example.vaccine;

import android.content.Context;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {
    private ArrayList<Vaccine> dataList;
    private Context context;

    public myAdapter(Context context, ArrayList<Vaccine> dataList) {
        this.dataList = dataList;
        this.context = context;
    }

    // View Holder class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView vaccineTitle;
        public TextView vaccinePrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.imageview);
            this.vaccineTitle = itemView.findViewById(R.id.textview);
            this.vaccinePrice = itemView.findViewById(R.id.price);
        }
    }
// step#3 Implementing the method


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listItem = inflater.inflate(R.layout.my_layout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(listItem);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        final Vaccine vaccineObj = dataList.get(position);
        holder.vaccineTitle.setText(dataList.get(position).getTitle());
        holder.vaccinePrice.setText(dataList.get(position).getPrice());
        holder.image.setImageResource(dataList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
