package com.example.assng4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Team.db";
    public static final String TABLE_NAME = "Team_table";
    public static final String COL_0 = "ID";
    public static final String COL_1 = "CITY";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SPORT";
    public static final String COL_4 = "MVP";
    public static final String COL_5 = "STADIUM";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, CITY TEXT, NAME TEXT, SPORT TEXT, MVP TEXT, STADIUM TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String city, String name, String sport, String mvp, String stadium) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, city);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, sport);
        contentValues.put(COL_4, mvp);
        contentValues.put(COL_5, stadium);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();

        if (result == -1) return false;
        else return true;
    }

    public List<String> getAllData(int index) {
        List<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while(res.moveToNext()) {
            list.add(res.getString(index));
        }
        res.close();
        db.close();
        return list;
    }

    public boolean updateData(String id, String city, String name, String sport, String mvp, String stadium) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, city);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, sport);
        contentValues.put(COL_4, mvp);
        contentValues.put(COL_5, stadium);
        long result = db.update(TABLE_NAME, contentValues, "ID = " + id, null);
        db.close();

        if (result == -1) return false;
        else return true;
    }


    public boolean deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        boolean IsDeleted = db.delete(TABLE_NAME, "ID = " + id , null) > 0;
        db.close();
        return IsDeleted;
    }

}
