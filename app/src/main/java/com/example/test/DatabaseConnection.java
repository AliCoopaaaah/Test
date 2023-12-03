package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DatabaseConnection extends SQLiteOpenHelper {
    private static final String DBName = "appdb";
    private static final int DBVersion = 1;
    private static final String TableName = "savedimages";
    private static final String id = "id";
    private static final String date = "date";
    private static final String url = "url";

    String createTable = "CREATE TABLE "+TableName+" ("+id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            date+" TEXT)";

    public DatabaseConnection(Context context){
        super(context, DBName, null, DBVersion);
    }

    public void addImage(String savedate, String saveurl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(date, savedate);
        values.put(url, saveurl);

        db.insert(TableName, null, values);

        db.close();
    }//addImages

    public Cursor getImages(){
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+TableName;
        Cursor results = db.rawQuery(query, null);

        return results;
    }//getImages

    public ArrayList<String> getImageDate(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM "+ TableName;
        Cursor cursor = db.rawQuery(query, null);
        ArrayList<String> dates = new ArrayList<String>();
        while(cursor.moveToNext()){
            String date = cursor.getString(1);
            dates.add(date);
        }
        cursor.close();
        db.close();
        return dates;
    }//getImageDates

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
