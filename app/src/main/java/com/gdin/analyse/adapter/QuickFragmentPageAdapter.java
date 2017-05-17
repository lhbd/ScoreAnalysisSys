package com.gdin.analyse.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

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
        flags = new boolean[fragments.size()];
        for (int i = 0;i<fragments.size();i++){
            flags[i] = false;
        }
        notifyDataSetChanged();
//        mStrings = titles;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //得到缓存的fragment
        Fragment fragment = (Fragment)super.instantiateItem(container, position);
        //得到tag ❶
        String fragmentTag = fragment.getTag();
        if (flags[position %flags.length]) {
            //如果这个fragment需要更新
            FragmentTransaction ft =fm.beginTransaction();
            //移除旧的fragment
            ft.remove(fragment);
            //换成新的fragment
            fragment =fragments.get(position %fragments.size());
            //添加新fragment时必须用前面获得的tag ❶
            ft.add(container.getId(), fragment, fragmentTag);
            ft.attach(fragment);
            ft.commit();
            //复位更新标志
            flags[position %flags.length] =false;
        }
        return fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return  fragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
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
    public  void updateFlags(int pos){
        flags[pos] = true;
    }
}