package com.maadlabs.flist.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maadlabs.flist.R;
import com.maadlabs.flist.model.LocalItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyListFragment extends Fragment implements AddListItemDialogFragment.ListItemIntf {


    public static final String TAG = MyListFragment.class.getName();
    RecyclerView mListItemsRecyclerView;
    View mView;

    public MyListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_list, container, false);
        initReferences();
        return mView;
    }

    private void initReferences() {

        mListItemsRecyclerView = (RecyclerView) mView.findViewById(R.id.list_items_recyclerview);
    }

    public static MyListFragment getInstance() {
        return new MyListFragment();
    }

    @Override
    public void onItemAdded(LocalItem item) {

    }
}
