package com.example.rohanmalik.finaltodo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.os.Handler;
import android.widget.TextView;
import android.widget.VideoView;

public class Main2Activity extends Activity {
    private final int SPLASH_LENGTH=2000;
    EditText editText;
    String name;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main2);
        textView = (TextView)findViewById(R.id.textView2);
        SharedPreferences preferences= getSharedPreferences("Todo",MODE_PRIVATE);
        final String newText = preferences.getString("nameKey",null);
        Button button = findViewById(R.id.buttt);
        editText = findViewById(R.id.editText);
            if(newText==null){
                textView.setText("USER");
            }
            else{
                textView.setText(newText);
                button.setVisibility(View.INVISIBLE);
                editText.setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                        Main2Activity.this.startActivity(intent);
                        Main2Activity.this.finish();
                        intent.putExtra("naaam",newText);
                    }
                },SPLASH_LENGTH);

            }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=editText.getText().toString();
                SharedPreferences.Editor editor = getSharedPreferences("Todo",MODE_PRIVATE).edit();
                editor.putString("nameKey",name);
                editor.commit();
                Intent intent = new Intent(Main2Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });



//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                    Intent intent =new Intent(Main2Activity.this,MainActivity.class);
//                Main2Activity.this.startActivity(intent);
//                Main2Activity.this.finish();
//            }
//        },SPLASH_LENGTH);
    }
}
