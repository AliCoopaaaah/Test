package com.example.test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseConnection extends SQLiteOpenHelper {
    private static final String DBName = "appdb";
    private static final int DBVersion = 1;
    private static final String TableName = "savedimages";
    private static final String id = "id";
    private static final String date = "date";
    private static final String url = "url";

    public DatabaseConnection(Context context){
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TableName+" ("+
                id+"INTEGER PRIMARY KEY AUTOINCREMENT, "+
                date+"TEXT,"+
                url+"TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TableName);
        onCreate(db);
    }

    public void onAddItem(String itemDate, String itemURL){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(date, itemDate);
        values.put(url, itemURL);

       long result =  db.insert(TableName, null, values);

    }
}
