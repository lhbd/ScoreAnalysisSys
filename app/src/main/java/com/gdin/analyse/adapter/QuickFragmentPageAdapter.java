package com.gdin.analyse.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class QuickFragmentPageAdapter<T extends Fragment> extends FragmentPagerAdapter {
    private List<T> fragments;
    private String[] mStrings;

    private FragmentManager fm;
    private boolean[] flags;//标识,重新设置fragment时全设为true

    /**
     * @param fm
     * @param fragments
     */
    public QuickFragmentPageAdapter(FragmentManager fm, List<T> fragments) {
        super(fm);
        this.fragments = fragments;
        this.fm = fm;
//        mStrings = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return  fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
//        return mStrings == null ? super.getPageTitle(position) : mStrings[position];
    }
}