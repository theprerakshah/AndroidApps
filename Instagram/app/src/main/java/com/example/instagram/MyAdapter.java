package com.example.instagram;

import android.content.Context;
import android.provider.ContactsContract;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<Insta> {
    Context context;
    ArrayList<Insta> myArray;

    public MyAdapter(ArrayList<Insta> myArray, Context context) {
        super(context, R.layout.mylayout, myArray);
        this.myArray = myArray;
        this.context = context;

    }

    private static class ViewHolder {
        TextView instaId, counts, caption;
        ImageView myImage, like, share;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View result;
        Insta myInsta = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.mylayout, parent, false);

            viewHolder.instaId = (TextView) convertView.findViewById(R.id.instaid);
            viewHolder.counts = (TextView) convertView.findViewById(R.id.counts);
            viewHolder.caption = (TextView) convertView.findViewById(R.id.caption);
            viewHolder.myImage = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.like = (ImageView) convertView.findViewById(R.id.like);
            viewHolder.share = (ImageView) convertView.findViewById(R.id.share);
            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            convertView.setTag((viewHolder));
        }
        viewHolder.instaId.setText(myInsta.getInstaId());
        viewHolder.counts.setText(myInsta.getCount());
        viewHolder.caption.setText(myInsta.getCaption());
        viewHolder.myImage.setImageResource(myInsta.getMyImage());
        viewHolder.share.setImageResource(myInsta.getShare());
        viewHolder.like.setImageResource(myInsta.getLike());
        return convertView;
    }
}
