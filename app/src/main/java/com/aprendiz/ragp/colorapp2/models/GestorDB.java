package com.aprendiz.ragp.colorapp2.models;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper{
    public GestorDB(Context context) {
        super(context, "strooper.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SCORE (PUNTUACION INTEGER, INCORRECTAS INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Score> listResults(){
        List<Score> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SCORE ORDER BY PUNTUACION ASC AND INCORRECTAS ASC;",null);
        if (cursor.moveToFirst()){
            do {

            }while (cursor.moveToNext());
        }

        return results;
    }
}
