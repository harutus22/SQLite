package com.example.sqlite.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myDatabase";
    public static final String TABLE_NAME = "ikea";
    public static final String id = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String MARK = "mark";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME +
               " (" + id + " INTEGER PRIMARY KEY autoincrement, " + NAME + " TEXT not null," +
                SURNAME + " TEXT not null," + MARK + " INTEGER not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
