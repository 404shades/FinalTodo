package com.example.rohanmalik.finaltodo;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddDetails extends AppCompatActivity {
    EditText dateSet;
    EditText EditTitle;
    Spinner spinner;
    Calendar myCalender;
    String[] spinnerList={"Birthday","Meeting","Reminder"};;
    ArrayAdapter adapter;
    Button buttonSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        myCalender = Calendar.getInstance();
        dateSet = (EditText)findViewById(R.id.DatePicker);
        spinner = (Spinner)findViewById(R.id.spinn);
        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,spinnerList);
        spinner.setAdapter(adapter);
        EditTitle = (EditText)findViewById(R.id.EditTitle);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalender.set(Calendar.YEAR,i);
                myCalender.set(Calendar.MONTH,i1);
                myCalender.set(Calendar.DAY_OF_MONTH,i2);
                updateLabel();
            }
        };
        dateSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddDetails.this,date,myCalender.get(Calendar.YEAR),myCalender.get(Calendar.MONTH),myCalender.get(Calendar.DATE)).show();
            }
        });
        buttonSubmit=(Button)findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = EditTitle.getText().toString();
                String Date = dateSet.getText().toString();
                String Category = spinner.getSelectedItem().toString();
                ToDoOpenHelper toDoOpenHelper = new ToDoOpenHelper(AddDetails.this);
                SQLiteDatabase database = toDoOpenHelper.getWritableDatabase();
                ContentValues cv =new ContentValues();
                int categ;
                if(Category=="Birthday"){
                    categ = R.drawable.cake;
                }
                else{
                    categ = R.drawable.mett;
                }
                cv.put(ToDoOpenHelper.TODO_TITLE,title);
                cv.put(ToDoOpenHelper.TODO_DATE,Date);
                cv.put(ToDoOpenHelper.TODO_CATEGORY,categ);
                database.insert(ToDoOpenHelper.TABLE_NAME,null,cv);
                setResult(RESULT_OK);
                finish();

            }
        });
    }
    public void updateLabel() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateSet.setText(sdf.format(myCalender.getTime()));

    }
}
