package com.example.shubham.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shubham on 17-02-2018.
 */

public class OpenHelper extends SQLiteOpenHelper {
    private static OpenHelper openHelper;

    public static OpenHelper getInstance(Context context){
        if(openHelper == null){
            openHelper = new OpenHelper(context.getApplicationContext());
        }
        return  openHelper;
    }

    public OpenHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null,Contract.VERSION);
    }
    @Override
    public void onConfigure(SQLiteDatabase sqLiteDatabase) {
        super.onConfigure(sqLiteDatabase);
        sqLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tasksql = "CREATE TABLE " + Contract.TaskClass.TABLE_NAME + "("
                + Contract.TaskClass.ID + " INTEGER PRIMARY KEY AUTOINCREMENT  , "
                + Contract.TaskClass.TITLE + " TEXT , "
                + Contract.TaskClass.COST + " INTEGER, "
                + Contract.TaskClass.DESCRIPTION + " TEXT)";
        sqLiteDatabase.execSQL(tasksql);
        String commentsSql = "CREATE TABLE " + Contract.Comments.TABLE_NAME + " ( " +
                Contract.Comments.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.Comments.COMMENT + " TEXT, " +
                Contract.Comments.TASK_ID + " INTEGER,  " +
                "FOREIGN KEY (" + Contract.Comments.TASK_ID + ") REFERENCES " + Contract.TaskClass.TABLE_NAME + " (" + Contract.TaskClass.ID + ") ON DELETE CASCADE)";
        sqLiteDatabase.execSQL(commentsSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
