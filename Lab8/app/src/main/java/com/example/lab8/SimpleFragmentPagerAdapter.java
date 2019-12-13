package com.example.lab8;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Fragment [] childFragments;

    public SimpleFragmentPagerAdapter(FragmentManager fm){
        super(fm);
        childFragments = new Fragment[] {
                new Fragment1(),
                new Fragment2(),
                new Fragment3()
        };
    }

    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length;
    }


}
