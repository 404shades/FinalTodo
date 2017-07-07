package com.example.rohanmalik.finaltodo;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ArrayList<ToDoRohan> ToDoList;
    ListView listView;
    ToDoAdapter toDoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String date= DateFormat.getDateInstance().format(new Date());
        TextView tv = (TextView)findViewById(R.id.textView5);
        tv.setText(date);
        TextView tvt = (TextView)findViewById(R.id.greetingg);
        String time = DateFormat.getDateTimeInstance().format(new Date());
        if(time.contains("AM")){
            tvt.setText("Good Morning Rohan");
        }
        else if(time.contains("PM")){
            tvt.setText("Good Evening Rohan");
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Add Details To your ToDo", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(MainActivity.this,AddDetails.class);
                startActivityForResult(intent,1);

            }
        });
        listView =(ListView)findViewById(R.id.ToDoList);
        ToDoList = new ArrayList<>();
        toDoAdapter = new ToDoAdapter(this,ToDoList);
        listView.setAdapter(toDoAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,Main3Activity.class);
                intent.putExtra("position",i);
                startActivity(intent);

            }
        });
        updateToDo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            updateToDo();
        }
    }
    public void updateToDo(){
        ToDoOpenHelper toDoOpenHelper = new ToDoOpenHelper(this);
        ToDoList.clear();
        SQLiteDatabase database = toDoOpenHelper.getReadableDatabase();
        Cursor cursor = database.query(ToDoOpenHelper.TABLE_NAME,null,null,null,null,null,null);
        while(cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(ToDoOpenHelper.TODO_TITLE));
            //String date = cursor.getString(cursor.getColumnIndex(ToDoOpenHelper.TODO_DATE));
            int category = cursor.getInt(cursor.getColumnIndex(ToDoOpenHelper.TODO_CATEGORY));
            ToDoRohan toDoRohan =  new ToDoRohan(title,category);
            ToDoList.add(toDoRohan);
        }
        toDoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do You Wish To exit The app");
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finishAndRemoveTask();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
