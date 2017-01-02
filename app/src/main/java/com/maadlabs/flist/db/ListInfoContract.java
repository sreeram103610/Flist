package com.maadlabs.flist.db;

import android.provider.BaseColumns;

/**
 * Created by brainfreak on 1/1/17.
 */

public final class ListInfoContract {

    private ListInfoContract() {
    }

    public static class ListInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "list";
        public static final String ITEM_ID = "item_id";
        public static final String USER_ID = "user_id";
        public static final String ITEM_NAME = "item_name";
        public static final String ITEM_SUB_CATEGORY = "item_sub_category";
        public static final String ITEM_QUANTITY = "item_quantity";
        public static final String ITEM_LIST_NAME = "item_list_name";
        public static final String ITEM_PURCHASED = "item_purchased";
    }
}
