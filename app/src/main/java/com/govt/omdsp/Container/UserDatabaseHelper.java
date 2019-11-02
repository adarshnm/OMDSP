package com.govt.omdsp.Container;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "omdsp.db";
    private static final String TABLE_NAME = "user_table";
    private static final String LOGIN_TABLE_NAME = "user_credentials";

    public UserDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("create table if not exists "+TABLE_NAME+" (username TEXT PRIMARY KEY, name TEXT,gender TEXT)");
        //sqLiteDatabase.execSQL("create table if not exists "+LOGIN_TABLE_NAME+" (username TEXT PRIMARY KEY, password TEXT, FOREIGN KEY (username) REFERENCES "+TABLE_NAME+" (username) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
