package com.example.mywhatsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    private int tabNumber;

    public MyAdapter(@NonNull FragmentManager fm, int tabNumber) {
        super(fm);
        this.tabNumber = tabNumber;
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
        return tabNumber;
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
