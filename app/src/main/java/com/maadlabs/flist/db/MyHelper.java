package com.maadlabs.flist.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by brainfreak on 1/1/17.
 */

public class MyHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS " + ListInfoContract.ListInfoEntry.TABLE_NAME
                                                + "(" + ListInfoContract.ListInfoEntry.ITEM_ID + " INTEGER PRIMARY KEY, "
                                                + ListInfoContract.ListInfoEntry.USER_ID + " INTEGER, "
                                                + ListInfoContract.ListInfoEntry.ITEM_LIST_NAME + " TEXT, "
                                                + ListInfoContract.ListInfoEntry.ITEM_NAME + " TEXT, "
                                                + ListInfoContract.ListInfoEntry.ITEM_PURCHASED + " BOOLEAN, "
                                                + ListInfoContract.ListInfoEntry.ITEM_QUANTITY + " INTEGER, "
                                                + ListInfoContract.ListInfoEntry.ITEM_SUB_CATEGORY + " TEXT)";

    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS " + ListInfoContract.ListInfoEntry.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "LocalDatabase.db";

    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_QUERY);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_QUERY);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
