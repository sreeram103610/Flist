package com.maadlabs.flist.fragment;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.maadlabs.flist.R;


public class AddListItemDialogFragment extends DialogFragment implements View.OnClickListener{

    public static final String TAG = "AddListItemDialogFragment";
    View mView;
    private Button mOkButton;
    private Button mCancelButton;
    private TextInputEditText mItemNameEditText;
    private TextInputEditText mItemSubCategoryEditText;
    private TextInputEditText mQuantityEditText;

    public AddListItemDialogFragment() {

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.add_list_item_layout, container, false);

        initReferences();
        initListeners();
        initViews();
        return mView;
    }

    private void initViews() {

    }

    private void initListeners() {

        mOkButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);
    }

    private void initReferences() {

        mOkButton = (Button) mView.findViewById(R.id.ok_button);
        mCancelButton = (Button) mView.findViewById(R.id.cancel_button);

        mItemNameEditText = (TextInputEditText) mView.findViewById(R.id.item_name_edit_text);
        mItemSubCategoryEditText = (TextInputEditText) mView.findViewById(R.id.item_subcategory_edit_text);
        mQuantityEditText = (TextInputEditText) mView.findViewById(R.id.quantity_edit_text);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.ok_button:
                validateFields();
                break;

            case R.id.cancel_button:
                dismiss();
                break;
        }
    }

    private void validateFields() {


    }
}
