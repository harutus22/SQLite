package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private List<Student> students = new ArrayList<>();
    private MySQLiteHelper mySQLiteHelper;
    private Context context;

    public DBManager(Context context){
        mySQLiteHelper = new MySQLiteHelper(context);
        this.context = context;
    }

    public void createStudentInfo(String name, String surname, int mark){
        if(mark <= 100) {
            SQLiteDatabase sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MySQLiteHelper.NAME, name);
            contentValues.put(MySQLiteHelper.SURNAME, surname);
            contentValues.put(MySQLiteHelper.MARK, mark);
            long id = sqLiteDatabase.insert(MySQLiteHelper.TABLE_NAME, null, contentValues);
            sqLiteDatabase.close();
            if (id > 0) {
                makeToast("Data saved");
            } else {
                makeToast("Data not saved");
            }
        } else {
            makeToast("Mark should be between 0 and 100");
        }
    }

    public List<Student> getAllInfo(){
        SQLiteDatabase sqLiteDatabase = mySQLiteHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(MySQLiteHelper.TABLE_NAME, null, null,
                null, null, null, null);
        Log.d("Tag", String.valueOf(cursor.getCount()));
        cursor.moveToFirst();

        do {
            int id = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.id));
            String name = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.NAME));
            String surname = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.SURNAME));
            int mark = cursor.getInt(cursor.getColumnIndex(MySQLiteHelper.MARK));
            Student student = new Student(id, name, surname, mark);
            students.add(student);
        } while ((cursor.moveToNext()));
        cursor.close();
        return students;
    }

    private void makeToast(String text){
        Toast.makeText(context,text, Toast.LENGTH_LONG).show();
    }
}
