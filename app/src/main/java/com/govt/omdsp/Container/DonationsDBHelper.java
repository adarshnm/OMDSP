package com.govt.omdsp.Container;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DonationsDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "omdsp.db";
    private static final String TABLE_NAME = "donations_table";

    public DonationsDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
