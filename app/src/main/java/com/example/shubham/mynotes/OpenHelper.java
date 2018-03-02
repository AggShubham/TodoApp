package com.example.shubham.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shubham on 17-02-2018.
 */

public class OpenHelper extends SQLiteOpenHelper {
    public OpenHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null,Contract.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tasksql = "CREATE TABLE " + Contract.TaskClass.TABLE_NAME + "("
                + Contract.TaskClass.ID + " INTEGER PRIMARY KEY AUTOINCREMENT  , "
                + Contract.TaskClass.TITLE + " TEXT , "
                + Contract.TaskClass.COST + " INTEGER, "
                + Contract.TaskClass.DESCRIPTION + " TEXT)";
        sqLiteDatabase.execSQL(tasksql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
