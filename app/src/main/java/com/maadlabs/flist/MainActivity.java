package com.maadlabs.flist;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;

import com.maadlabs.flist.fragment.AddListItemDialogFragment;
import com.maadlabs.flist.fragment.FeedsFragment;
import com.maadlabs.flist.fragment.MyListFragment;
import com.maadlabs.flist.model.MyFragmentAdapter;

public class MainActivity extends FragmentActivity {

    MyFragmentAdapter mMyFragmentAdapter;
    ViewPager mViewPager;
    TabLayout mTabLayout;
    FloatingActionButton mActionButton;
    AddListItemDialogFragment mAddListItemDialogFragment;
    MyListFragment mListItemsFragment;

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

        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mViewPager.getCurrentItem() == 0)
                    mAddListItemDialogFragment.setTargetFragment(mListItemsFragment, 0);
                mAddListItemDialogFragment.show(getSupportFragmentManager(), AddListItemDialogFragment.TAG);
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
        mActionButton = (FloatingActionButton) findViewById(R.id.floating_button);
    }

    private void initData() {
        mMyFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mAddListItemDialogFragment = new AddListItemDialogFragment();
        mListItemsFragment = (MyListFragment) getSupportFragmentManager().findFragmentByTag(MyListFragment.TAG);
    }

}
