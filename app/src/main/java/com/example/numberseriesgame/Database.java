package com.example.numberseriesgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String user_db = "Games";
    public static final int version_db = 2;

    public static final String Games_tb_name = "data";
    public static final String Games_tb_fullname = "full_name";
    public static final String Games_tb_Date = "Date";
    public static final String Games_tb_score = "score";

    public Database(Context c) {

        super(c, user_db, null, version_db);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create Table " + Games_tb_name + "(" + Games_tb_fullname + "  Text," + "" + Games_tb_Date + " Text," + Games_tb_score + " Integer )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    /* دالة اضافة*/
    public boolean addition(Atributes atributes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Games_tb_fullname, atributes.getName());
        values.put(Games_tb_Date, atributes.getDate());
        values.put(Games_tb_score, atributes.getScore());
        long result = db.insert(Games_tb_name, null, values);
        return result != -1;
    }


    /*دالة الاسترجاع أو (الاستعلام  select)*/
    ArrayList<Atributes> getAlldata() {
        ArrayList<Atributes> atributes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * from " + Games_tb_name, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(Games_tb_fullname));
                String Date = cursor.getString(cursor.getColumnIndex(Games_tb_Date));
                int score = cursor.getInt(cursor.getColumnIndex(Games_tb_score));
                Atributes atrib = new Atributes(score, name, Date);
                atributes.add(atrib);

            } while (cursor.moveToNext());
            cursor.close();

        }


        return atributes;
    }



    /*دالة الحذف*/
    public boolean delete_tb() {
        SQLiteDatabase db = getWritableDatabase();
       int result = db.delete(Games_tb_name, null, null);
        return result > 0;

    }
}
