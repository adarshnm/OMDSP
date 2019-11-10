package com.govt.omdsp.Container;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DonationsDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "donor.db";
    private static final String TABLE_NAME = "donations_table";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_NAME = "name";
    private static final String KEY_NGO = "ngo";
    private static final String KEY_DATE = "date";
    private static final String KEY_TABLETS = "tablets";
    private static final String KEY_SYRUPS = "syrups";
    private static final String KEY_SANITARY = "sanitary";
    private static final String KEY_OTHERS = "others";
    private static final String KEY_STATUS = "status";


    public DonationsDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_USERNAME + " TEXT,"
                + KEY_NAME + " TEXT," + KEY_NGO + " TEXT," + KEY_DATE + " TEXT," + KEY_TABLETS + " INTEGER,"
                + KEY_SYRUPS + " INTEGER," + KEY_SANITARY + " INTEGER,"
                + KEY_OTHERS + " INTEGER," + KEY_STATUS + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertDonor(String name, String username, String ngo, String tab, String syr, String san, String oth) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_USERNAME, username);
        cv.put(KEY_NAME, name);
        cv.put(KEY_NGO, ngo);
        cv.put(KEY_DATE, formatter.format(date));
        cv.put(KEY_TABLETS, tab);
        cv.put(KEY_SYRUPS, syr);
        cv.put(KEY_SANITARY, san);
        cv.put(KEY_OTHERS, oth);
        cv.put(KEY_STATUS, "Not Picked");
        long res = db.insert(TABLE_NAME, null, cv);
        return res != -1;
    }

    public boolean updateDonorStatus(String id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_STATUS, "Picked");
        long res = db.update(TABLE_NAME, cv, KEY_ID + "=?", new String[]{id});
        return res != -1;
    }

    // Get Donation Details
    public ArrayList<HashMap<String, String>> GetDonations() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> donorList = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> donor = new HashMap<>();
            donor.put("id", cursor.getString(cursor.getColumnIndex(KEY_ID)));
            donor.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            donor.put("username", cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            donor.put("ngo", cursor.getString(cursor.getColumnIndex(KEY_NGO)));
            donor.put("date", cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            donor.put("tablets", cursor.getString(cursor.getColumnIndex(KEY_TABLETS)));
            donor.put("syrups", cursor.getString(cursor.getColumnIndex(KEY_SYRUPS)));
            donor.put("sanitary", cursor.getString(cursor.getColumnIndex(KEY_SANITARY)));
            donor.put("other", cursor.getString(cursor.getColumnIndex(KEY_OTHERS)));
            donor.put("status", cursor.getString(cursor.getColumnIndex(KEY_STATUS)));
            donorList.add(donor);
        }
        cursor.close();
        return donorList;
    }

    public ArrayList<HashMap<String, String>> GetDonationsForUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> donorList = new ArrayList<>();
        String query = "SELECT " + KEY_NAME + "," + KEY_NGO + "," + KEY_DATE + " FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> donor = new HashMap<>();
            donor.put("name", cursor.getString(cursor.getColumnIndex(KEY_NAME)));
            donor.put("ngo", cursor.getString(cursor.getColumnIndex(KEY_NGO)));
            donor.put("date", cursor.getString(cursor.getColumnIndex(KEY_DATE)));
            donorList.add(donor);
        }
        cursor.close();
        return donorList;
    }
}
