package com.maadlabs.flist.fragment;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.maadlabs.flist.R;
import com.maadlabs.flist.db.ListInfoContract;
import com.maadlabs.flist.db.MyHelper;
import com.maadlabs.flist.model.Item;
import com.maadlabs.flist.model.LocalItem;


public class AddListItemDialogFragment extends DialogFragment implements View.OnClickListener{

    public static final String TAG = "AddListItemDialogFragment";
    View mView;
    private Button mOkButton;
    private Button mCancelButton;
    private TextInputEditText mItemNameEditText;
    private TextInputEditText mItemSubCategoryEditText;
    private TextInputEditText mQuantityEditText;
    private MyHelper mDatabaseHelper;
    private SQLiteDatabase mDatabase;

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
        initData();
        return mView;
    }

    private void initData() {

        mDatabaseHelper = new MyHelper(getContext());
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
                LocalItem newItem = addItemToDb();
                if (newItem != null)
                    passDataToFragment(newItem);
                dismiss();
                break;

            case R.id.cancel_button:
                dismiss();
                break;
        }
    }

    private void passDataToFragment(LocalItem newItem) {

        ListItemIntf listItemIntf = (ListItemIntf) getTargetFragment();

        if (listItemIntf == null)
            return;

        listItemIntf.onItemAdded(newItem);
    }

    private LocalItem addItemToDb() {

        LocalItem item = new LocalItem();
        item.setName(mItemNameEditText.getText().toString());
        item.setQuantity(Integer.parseInt(mQuantityEditText.getText().toString()));
        item.setItemPurchased(false);
        item.setType(mItemSubCategoryEditText.getText().toString());

        if (mDatabase == null) {
            mDatabase = mDatabaseHelper.getWritableDatabase();
        }

        // TODO: sort out list name

        ContentValues values = new ContentValues();
        values.put(ListInfoContract.ListInfoEntry.ITEM_NAME, item.getName());
        values.put(ListInfoContract.ListInfoEntry.ITEM_QUANTITY, item.getQuantity());
        values.put(ListInfoContract.ListInfoEntry.ITEM_PURCHASED, item.isItemPurchased());
        values.put(ListInfoContract.ListInfoEntry.ITEM_SUB_CATEGORY, item.getType());

        long rowId = mDatabase.insert(ListInfoContract.ListInfoEntry.TABLE_NAME, null, values);

        if (rowId == -1)
            return null;

        return item;
    }

    private void validateFields() {

        String arg1 = null;
        if(mItemNameEditText.getText().length() == 0) {
            arg1 = getString(R.string.item_name);
        } else if (mQuantityEditText.getText().length() == 0) {
            arg1 = getString(R.string.quantity);
        }
        Toast.makeText(getContext(), getString(R.string.item_name_attention_message_dialog_fragment, arg1), Toast.LENGTH_SHORT).show();

    }


    public interface ListItemIntf {
        void onItemAdded(LocalItem item);
    }
}
