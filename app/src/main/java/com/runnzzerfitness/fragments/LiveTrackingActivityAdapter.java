package com.runnzzerfitness.fragments;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class LiveTrackingActivityAdapter extends FragmentPagerAdapter {


    private Fragment fragments [] = {
      new MapFragment(),
      new LiveDataFragment()
    };


    private String titles [] = {
            "Map" ,
            "Live Data"
    };


    public LiveTrackingActivityAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }


    @Override
    public int getCount() {
        return fragments.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
