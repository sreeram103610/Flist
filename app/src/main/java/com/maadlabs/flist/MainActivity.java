package com.maadlabs.flist;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.maadlabs.flist.model.MyFragmentAdapter;

public class MainActivity extends FragmentActivity {

    MyFragmentAdapter mMyFragmentAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initReferences();
        initData();
        initListeners();
        initViews();
    }

    private void initListeners() {
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initViews() {
        mViewPager.setAdapter(mMyFragmentAdapter);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.main_activity_list), 0, true);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.main_activity_feeds), 1);
    }

    private void initReferences() {
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
    }

    private void initData() {
        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
    }

}
