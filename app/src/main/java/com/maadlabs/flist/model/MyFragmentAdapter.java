package com.maadlabs.flist.model;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maadlabs.flist.fragment.FeedsFragment;
import com.maadlabs.flist.fragment.MyListFragment;

/**
 * Created by brainfreak on 12/31/16.
 */

public class MyFragmentAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 2;
    MyListFragment mListFragment;
    FeedsFragment mFeedsFragment;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
        mListFragment = MyListFragment.getInstance();
        mFeedsFragment = FeedsFragment.getInstance();
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return mListFragment;
            case 1:
                return mFeedsFragment;
        }
        return null;
    }


    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}
