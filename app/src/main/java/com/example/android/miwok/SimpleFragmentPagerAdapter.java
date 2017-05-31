package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by hp on 15-05-2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1){
            return new ColorsFragment();
        } else
        {
            return new FamilyFragment();
        }

    }

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount()
    {
        return 3;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}

