package com.example.worldcup;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.worldcup.Country;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Country> {
    private ArrayList<Country> myobjectlist;
    Context context;

    public ArrayList<Country> getMyobjectlist() {
        return myobjectlist;
    }

    public void setMyobjectlist(ArrayList<Country> myobjectlist) {
        this.myobjectlist = myobjectlist;
    }

    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public MyAdapter(ArrayList<Country> mylist, Context context) {
        super(context, R.layout.mylayout, mylist);
        this.myobjectlist = mylist;
        this.context = context;
    }

    //view lookup cache
    private static class ViewHolder {
        TextView country;
        TextView wins;
        ImageView flag;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);

        //Get data item for this position
        Country dataModel = getItem(position);

        // check if exsiting view is being reused, other wise inflate the view
        ViewHolder viewHolder;
        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mylayout, null, true);
            viewHolder.country = (TextView) convertView.findViewById(R.id.country);
            viewHolder.wins = (TextView) convertView.findViewById(R.id.win);
            viewHolder.flag = (ImageView) convertView.findViewById(R.id.flag);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        //get data from the model class
        viewHolder.country.setText(dataModel.getCountryName());
        viewHolder.wins.setText(dataModel.getWin());
        viewHolder.flag.setImageResource(dataModel.getFlag());
        return convertView;
    }
}
