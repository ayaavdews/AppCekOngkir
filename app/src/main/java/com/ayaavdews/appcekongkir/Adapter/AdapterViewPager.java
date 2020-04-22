package com.ayaavdews.appcekongkir.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/*
* Create By : Aya Avdews
* XII RPL A / 17.006892
* SMKN 2 Surakarta
* */

public class AdapterViewPager extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();

    public AdapterViewPager(FragmentManager manager){
        super(manager);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }
}
