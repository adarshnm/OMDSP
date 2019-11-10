package com.govt.omdsp.Container;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "omdsp.db";
    private static final String TABLE_NAME = "user_table";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PASS = "password";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_STATE = "state";

    public UserDatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists "+TABLE_NAME+" ("
                +KEY_USERNAME+" TEXT PRIMARY KEY, "+KEY_NAME+" TEXT,"
                + KEY_PHONE + " INTEGER,"
                +KEY_PASS+" TEXT,"+KEY_GENDER+" TEXT,"
                +KEY_ADDRESS+" TEXT,"+KEY_STATE+" TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    public boolean insertUser(String username, String name, String phone, String password, String gender, String address, String state)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME,username);
        values.put(KEY_NAME,name);
        values.put(KEY_PHONE,phone);
        values.put(KEY_PASS,password);
        values.put(KEY_GENDER,gender);
        values.put(KEY_ADDRESS,address);
        values.put(KEY_STATE,state);
        long res = db.insert(TABLE_NAME,null,values);
        return res != -1;
    }
    public Cursor getUser(String username,String password)
    {
        SQLiteDatabase db  = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select "+KEY_NAME+" from "+TABLE_NAME+" where "+KEY_USERNAME+"=? and "
                + KEY_PASS + "=?", new String[]{username + "", password + ""});
        return cursor;
    }

    public boolean checkUserExist(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select " + KEY_NAME + " from " + TABLE_NAME + " where " + KEY_USERNAME + "=?", new String[]{username + ""});
        int count = cursor.getCount();
        cursor.close();
        return count != 0;
    }

}
