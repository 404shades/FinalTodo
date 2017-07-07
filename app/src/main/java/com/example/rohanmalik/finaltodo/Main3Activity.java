package com.example.rohanmalik.finaltodo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
        TextView textViewtitle;
        TextView textViewDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        textViewtitle = (TextView)findViewById(R.id.textView3);
        textViewDate = (TextView)findViewById(R.id.textView4);
        ToDoOpenHelper toDoOpenHelper = new ToDoOpenHelper(this);
        SQLiteDatabase database = toDoOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(ToDoOpenHelper.TABLE_NAME,null,null,null,null,null,null);
        int position = getIntent().getExtras().getInt("position");
        cursor.moveToPosition(position);
        textViewtitle.setText(cursor.getString(cursor.getColumnIndex(ToDoOpenHelper.TODO_TITLE)));
        textViewDate.setText(cursor.getString(cursor.getColumnIndex(ToDoOpenHelper.TODO_DATE)));

    }
}
