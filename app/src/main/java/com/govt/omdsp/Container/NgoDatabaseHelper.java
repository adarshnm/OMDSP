package com.govt.omdsp.Container;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NgoDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "omdsp2.db";
    private static final String TABLE_NAME = "ngo_details";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_ADDRESS = "address";

    public NgoDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + " (" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT," + KEY_ADDRESS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_ADDRESS, address);
        long res = db.insert(TABLE_NAME, null, cv);
        return res != -1;
    }

    public ArrayList<HashMap<String, String>> getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> ngoList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> ngo = new HashMap<>();
            ngo.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            ngo.put("address", cursor.getString(cursor.getColumnIndex(KEY_ADDRESS)));
            ngoList.add(ngo);
        }
        cursor.close();
        return ngoList;
    }

    public List<String> getNGO() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<String> ngoList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select " + KEY_NAME + " from " + TABLE_NAME + "", null);
        while (cursor.moveToNext()) {
            ngoList.add(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
        }
        cursor.close();
        return ngoList;
    }

}
