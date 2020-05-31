package com.example.shoppinglistapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DatabaseSchema.Schemas.TABLE_NAME + " (" +
                    DatabaseSchema.Schemas._ID + " INTEGER PRIMARY KEY," +
                    DatabaseSchema.Schemas.COLUMN_NAME_NAME + " TEXT," +
                    DatabaseSchema.Schemas.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    DatabaseSchema.Schemas.COLUMN_NAME_AMOUNT + " INTEGER)";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseSchema.Schemas.TABLE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
