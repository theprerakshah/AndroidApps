package com.example.tablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {

    private int numerTab;

    public MyAdapter(@NonNull FragmentManager fm, int numerTab) {
        super(fm);
        this.numerTab = numerTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChatFragment();
            case 1:
                return new StatusFragment();
            case 2:
                return new CallFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numerTab;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                return title = "Chat";
            case 1:
                return title = "Status";
            case 2:
                return title = "Call";
            default:
                return title = "";


        }
    }
}
