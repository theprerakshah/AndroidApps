package com.example.weatherapp.recyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weatherapp.R;
import com.example.weatherapp.appmodle.Forecastday;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<Forecastday> forecastday;

    public MyAdapter(Context context, ArrayList<Forecastday> weatherList) {
        this.context = context;
        this.forecastday = weatherList;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

        Picasso.get().load("https://" + forecastday.get(position).getDay().getCondition().getIcon()).into(holder.image_temp);
        holder.text_airflow.setText(forecastday.get(position).getDay().getMaxwindKph() + " KMPH");
        holder.text_temp.setText(forecastday.get(position).getDay().getAvgtempC() + "°C");
        try {
//          Date date = input.parse(forecastday.get(position).getDate());
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(forecastday.get(position).getDate());
            holder.text_day.setText(getDayNumberOld(date));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        holder.text_day.setText(forecastday.get(position).getDay().);
    }

    public static String getDayNumberOld(Date date) {
        String day = "";
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int nu = cal.get(Calendar.DAY_OF_WEEK);
        switch (nu) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;

            default:
                day = "day not detected";
                break;
        }
        return day;
    }

    @Override
    public int getItemCount() {
        return forecastday.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_day, text_airflow, text_temp;
        ImageView image_temp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_day = itemView.findViewById(R.id.idTV_time);
            text_airflow = itemView.findViewById(R.id.idTV_AirFlow);
            text_temp = itemView.findViewById(R.id.idTV_temp);
            image_temp = itemView.findViewById(R.id.idIV_temperature);
        }
    }
}
