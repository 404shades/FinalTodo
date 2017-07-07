package com.example.rohanmalik.finaltodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rohan Malik on 30-06-2017.
 */

public class ToDoOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME="ToDoRohan";
    public static final String TODO_TITLE="TodoTitle";
    public static final String TODO_DATE="TodoDate";
    public static final String TODO_CATEGORY = "ToDoCategory";
    public static final String TODO_ID="_id";
    public ToDoOpenHelper(Context context) {
        super(context, "ToDo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "create table " + TABLE_NAME + " ( " + TODO_ID + " integer primary key autoincrement, " + TODO_TITLE + " text, "
                + TODO_DATE + " text, " + TODO_CATEGORY + " integer);";
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
