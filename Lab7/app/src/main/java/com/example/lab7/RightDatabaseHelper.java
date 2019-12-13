package com.example.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RightDatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    public static final String DATABASE_NAME = "Right.db";
    public static final String TABLE_NAME = "Right_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "DEPT";
    public static final String COL_3 = "YEAR";

    public RightDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (NAME TEXT, DEPT TEXT, YEAR INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String dept, String year) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, dept);
        contentValues.put(COL_3, year);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
}
