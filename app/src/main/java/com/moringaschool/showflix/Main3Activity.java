package com.moringaschool.showflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent=getIntent();
        String text=intent.getStringExtra(Main2Activity.EXTRA_TEXT);
        TextView textView1=(TextView)  findViewById(R.id.textView1);
        textView1.setText(text);
    }

}
