package com.example.rohanmalik.finaltodo;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Rohan Malik on 30-06-2017.
 */

public class ToDoAdapter extends ArrayAdapter<ToDoRohan> {
    ArrayList<ToDoRohan> toDoList;
    Context context;
    public ToDoAdapter(@NonNull Context context, ArrayList<ToDoRohan> toDoList) {
        super(context,0,toDoList);
        this.toDoList=toDoList;
        this.context=context;
    }
    static class ToDoRohanHolder{
        TextView titleView;
        ImageView imageView;

        public ToDoRohanHolder(TextView titleView, ImageView imageView) {
            this.titleView = titleView;
            this.imageView = imageView;
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.todo_item,null);
            TextView toDoTitle = (TextView)convertView.findViewById(R.id.titleBhai);
            ImageView toDoImage = (ImageView)convertView.findViewById(R.id.categoryBhai);
            ToDoRohanHolder toDoRohanHolder = new ToDoRohanHolder(toDoTitle,toDoImage);
            convertView.setTag(toDoRohanHolder);
        }
            ToDoRohan toDoRohan = toDoList.get(position);
            ToDoRohanHolder toDoRohanHolder=(ToDoRohanHolder)convertView.getTag();
            toDoRohanHolder.titleView.setText(toDoRohan.Title);
            toDoRohanHolder.imageView.setImageResource(toDoRohan.Category);
            return convertView;
    }
}
