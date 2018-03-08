package org.domain.name.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * 2017/11/11
 * By Liux
 * lx0758@qq.com
 */

public class TabAdapter extends FragmentPagerAdapter {
    private Fragment[] mFragments;

    public TabAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }
}
